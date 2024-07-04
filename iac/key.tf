###### ssh ######
# ssh-keygen -t rsa -b 2048 -f "imechanic-server.key"

resource "aws_key_pair" "imechanic-server-ssh" {
  key_name   = "${var.name_global_resource}-server-ssh"
  public_key = file("${var.name_global_resource}-server.key.pub")

  tags = {
    Name        = "${var.name_global_resource}-server"
    Environment = "test"
    Owner       = "nothicc04@gmail.com"
  }

}