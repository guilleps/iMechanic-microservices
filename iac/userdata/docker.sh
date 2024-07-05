#!/bin/bash
sudo yum update -y
sudo yum install -y docker
sudo service docker start
sudo systemctl enable docker
sudo usermod -aG docker ec2-user
sudo curl -L "https://github.com/docker/compose/releases/download/v2.19.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo yum install -y git
sudo yum install -y nginx
sudo systemctl enable nginx
sudo systemctl start nginx

# Configuración de NGINX
sudo bash -c 'cat > /etc/nginx/conf.d/default.conf <<EOL
server {
    listen 80;
    server_name localhost;
    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto \$scheme;
    }
}
EOL'
sudo systemctl restart nginx

# Clonar y levantar el contenedor
cd /home/ec2-user
if [ -d "docker-mechanic" ]; then
  sudo rm -rf docker-mechanic
fi
git clone https://github.com/RacoonDevRock/docker-mechanic.git
cd docker-mechanic
sudo docker-compose up -d

# docker --version
# docker-compose --version
# docker ps
