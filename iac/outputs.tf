output "aws_security_group_id" {
  description = "ID Security Group"
  value       = aws_security_group.imechanic-server-sg.id
}

output "imechanic_server_public_ip" {
  description = "IP Publica instance imechanic"
  value       = aws_instance.backend-imechanic.public_ip
}