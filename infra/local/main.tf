terraform {
  required_version = ">= 1.5"

  required_providers {
    aws = {
      source = "hashicorp/aws"
      # aws_dsql_cluster is available from provider v5.80+.
      version = ">= 5.80"
    }
  }

  # Local state on purpose: a throwaway DSQL used only to run DBFlute AlterCheck
  # from a developer's machine. Separate state from infra/dev.
}

provider "aws" {
  region = var.region
}
