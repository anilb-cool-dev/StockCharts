mkdir java
mkdir java/stockcharts
sudo yum install -y git
cd java/stockcharts
git clone https://github.com/anilb-cool-dev/StockCharts .
sudo amazon-linux-extras install java-openjdk11
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven