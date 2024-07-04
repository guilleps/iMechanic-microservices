resource "aws_instance" "backend-imechanic" {
  ami           = var.ami_instance
  instance_type = var.instance_type_aws
  key_name      = aws_key_pair.imechanic-server-ssh.key_name

  user_data = file("${path.module}/userdata/docker.sh")

  vpc_security_group_ids = [aws_security_group.imechanic-server-sg.id]

  tags = {
    Name        = "backend-imechanic"
    Environment = "test"
    Owner       = "nothicc04@gmail.com"
  }
}
