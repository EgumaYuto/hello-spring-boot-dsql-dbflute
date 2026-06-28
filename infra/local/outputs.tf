output "dsql_cluster_identifier" {
  value = aws_dsql_cluster.main.identifier
}

output "dsql_endpoint" {
  description = "Aurora DSQL connection endpoint (PostgreSQL, port 5432, IAM auth)."
  value       = local.dsql_endpoint
}

output "dsql_jdbc_url" {
  description = "JDBC URL for the Aurora DSQL connector (append ?user=admin for the admin role)."
  value       = local.dsql_jdbc_url
}
