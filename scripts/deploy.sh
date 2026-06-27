#!/usr/bin/env bash
#
# Deploy the Aurora DSQL + Lambda sandbox in one command:
#   build Lambda zip -> terraform apply (DSQL cluster, Lambda, Function URL, IAM)
#   -> provision the DSQL schema (dsqlInit)
#
# Aurora DSQL is serverless (scales to zero) and Lambda is pay-per-request, so
# there is NO VPC / NAT / ALB and idle cost is ~$0.
#
# Deploys the JSON API only. The React frontend is NOT served from Lambda (the
# serverless container can't return correct content types for a browser SPA over a
# Function URL — see README); run it locally pointed at this API with
# ./scripts/frontend-lambda.sh.
#
# Prerequisites:
#   - AWS profile valid (override with AWS_PROFILE=...), region supports DSQL
#   - JDK to launch Gradle (build targets Java 21; the wrapper provisions it)
#
# Usage: ./scripts/deploy.sh
#
source "$(dirname "${BASH_SOURCE[0]}")/lib.sh"

require_aws

# 1. build the Lambda deployment package (classes at root, deps under lib/)
log "Building Lambda deployment package"
( cd "$ROOT" && ./gradlew lambdaZip --console=plain )

# 2. apply the infrastructure (DSQL cluster + Lambda + Function URL + IAM)
log "Applying Terraform (region: $REGION)"
tf init -input=false >/dev/null
tf apply -auto-approve -input=false -var "region=$REGION"

# 3. provision the DSQL schema out-of-band (Flyway is not run inside the Lambda;
#    see application.yml / README for why). dsqlInit uses the DSQL JDBC connector
#    to authenticate with the ambient AWS credentials.
ENDPOINT="$(tf output -raw dsql_endpoint)"
log "Provisioning DSQL schema ($ENDPOINT)"
( cd "$ROOT" && DSQL_JDBC_URL="jdbc:aws-dsql:postgresql://${ENDPOINT}:5432/postgres?user=admin" \
    ./gradlew dsqlInit --console=plain )

URL="$(tf output -raw function_url)"
log "Deployed (API only)."
echo "    curl  ${URL}api/health    # -> Hello World! (quick health check)"
echo "    ./scripts/frontend-lambda.sh   # run the React UI locally against this API"
echo
echo "Note: the first request is a JVM/Spring Boot cold start and may take a few seconds."
