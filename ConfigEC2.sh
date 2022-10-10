mkdir java
mkdir java/stockcharts
cd java/stockcharts
sudo yum install -y git
git clone https://github.com/anilb-cool-dev/StockCharts .
sudo amazon-linux-extras install -y java-openjdk11
sudo amazon-linux-extras install -y tomcat9
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn package
sudo service tomcat start