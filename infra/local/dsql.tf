# DSQL-only stack for the "local" environment: a real Aurora DSQL cluster a developer
# can target from their machine to run DBFlute AlterCheck against DSQL's actual SQL
# constraints. No Lambda / Function URL / SSM / IAM — those belong to infra/dev.
resource "aws_dsql_cluster" "main" {
  # No deletion protection: spin up for an alter-check, tear down freely.
  deletion_protection_enabled = false

  tags = {
    Name        = var.app_name
    Environment = "local"
  }
}

locals {
  # Aurora DSQL connection endpoint follows a fixed hostname convention.
  dsql_endpoint = "${aws_dsql_cluster.main.identifier}.dsql.${var.region}.on.aws"

  # JDBC URL for the Aurora DSQL connector (handles IAM token auth transparently).
  dsql_jdbc_url = "jdbc:aws-dsql:postgresql://${local.dsql_endpoint}:5432/postgres"
}
