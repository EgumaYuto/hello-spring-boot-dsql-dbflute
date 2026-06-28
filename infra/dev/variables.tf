variable "app_name" {
  type    = string
  default = "hello-spring-boot-dsql-dbflute"
}

variable "region" {
  type    = string
  default = "ap-northeast-1"
}

# Path to the Lambda deployment package built by `./gradlew lambdaZip`.
variable "lambda_zip_path" {
  type    = string
  default = "../../build/dist/function.zip"
}
