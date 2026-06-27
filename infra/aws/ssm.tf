# JWT signing secret.
#
# A random value is generated and stored as an SSM SecureString. The Lambda reads
# it at startup via spring-cloud-aws (spring.config.import: aws-parameterstore in
# application-dsql.yml), so the secret is never committed to the repo and is not
# placed in the function's environment variables either.
resource "random_password" "jwt_secret" {
  length  = 64
  special = false
}

resource "aws_ssm_parameter" "jwt_secret" {
  name  = "/${var.app_name}/jwt-secret"
  type  = "SecureString"
  value = random_password.jwt_secret.result
}
