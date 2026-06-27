data "aws_iam_policy_document" "lambda_assume_role" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type        = "Service"
      identifiers = ["lambda.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "lambda" {
  name               = "${var.app_name}-lambda"
  assume_role_policy = data.aws_iam_policy_document.lambda_assume_role.json
}

# Basic CloudWatch Logs permissions.
resource "aws_iam_role_policy_attachment" "lambda_logs" {
  role       = aws_iam_role.lambda.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

# Aurora DSQL IAM auth: allow connecting as the admin database role.
# This replaces the password/Secrets Manager approach entirely.
data "aws_iam_policy_document" "dsql_connect" {
  statement {
    actions   = ["dsql:DbConnectAdmin"]
    resources = [aws_dsql_cluster.main.arn]
  }
}

resource "aws_iam_role_policy" "dsql_connect" {
  name   = "${var.app_name}-dsql-connect"
  role   = aws_iam_role.lambda.id
  policy = data.aws_iam_policy_document.dsql_connect.json
}

data "aws_caller_identity" "current" {}

# Read the JWT secret (SSM SecureString) at startup. spring-cloud-aws imports by
# path (`aws-parameterstore:/<app>/`), which calls ssm:GetParametersByPath, so we
# grant it on the path (and its children) as well as GetParameter on the parameter
# itself. kms:Decrypt is needed to decrypt the SecureString and is scoped to the
# SSM service via the kms:ViaService condition (covers the aws/ssm managed key
# without a chicken-and-egg data lookup before the key exists).
data "aws_iam_policy_document" "ssm_read" {
  statement {
    actions = [
      "ssm:GetParameter",
      "ssm:GetParameters",
      "ssm:GetParametersByPath",
    ]
    resources = [
      aws_ssm_parameter.jwt_secret.arn,
      "arn:aws:ssm:${var.region}:${data.aws_caller_identity.current.account_id}:parameter/${var.app_name}",
      "arn:aws:ssm:${var.region}:${data.aws_caller_identity.current.account_id}:parameter/${var.app_name}/*",
    ]
  }
  statement {
    actions   = ["kms:Decrypt"]
    resources = ["*"]
    condition {
      test     = "StringEquals"
      variable = "kms:ViaService"
      values   = ["ssm.${var.region}.amazonaws.com"]
    }
  }
}

resource "aws_iam_role_policy" "ssm_read" {
  name   = "${var.app_name}-ssm-read"
  role   = aws_iam_role.lambda.id
  policy = data.aws_iam_policy_document.ssm_read.json
}
