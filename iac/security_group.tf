resource "aws_security_group" "imechanic-server-sg" {
  name        = "${var.name_global_resource}-server-sg"
  description = "Security group allowing SSH and HTTP access"

  ingress = [
    {
      description      = "Allow SSH access from anywhere"
      from_port        = 22
      to_port          = 22
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow SSH access from anywhere"
      from_port        = 443
      to_port          = 443
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow HTTP access from anywhere"
      from_port        = 80
      to_port          = 80
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow HTTP access from anywhere"
      from_port        = 8080
      to_port          = 8080
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow HTTP access from anywhere"
      from_port        = 8761
      to_port          = 8761
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow HTTP access from anywhere"
      from_port        = 3307
      to_port          = 3307
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    },
    {
      description      = "Allow HTTP access from anywhere"
      from_port        = 27018
      to_port          = 27018
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    }
  ]

  egress = [
    {
      description      = "Allow all outbound traffic"
      from_port        = 0
      to_port          = 0
      protocol         = "-1"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = ["::/0"]
      prefix_list_ids  = []
      security_groups  = []
      self             = true
    }
  ]

  tags = {
    Name        = "${var.name_global_resource}-server"
    Environment = "test"
    Owner       = "nothicc04@gmail.com"
  }
}