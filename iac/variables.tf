############ CREDENTIALS AWS ############

variable "aws_region" {
  description = "Thw AWS region to deploy resource in"
  default     = "************"
}

variable "aws_profile" {
  description = "The AWS CLI profile to use"
  default     = "************"
}

############ INSTANCE EC2 ############

variable "ami_instance" {
  description = "AMI instance EC2"
  default     = "************"
}

variable "instance_type_aws" {
  description = "Type of instance EC2"
  default     = "************"
}

variable "name_global_resource" {
  description = "Name of domain about resources"
  default     = "************"
}
