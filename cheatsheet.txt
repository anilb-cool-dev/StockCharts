Cheat sheet
=================================================================================================
How do I map public domain to AWS?

Go to GoDaddy - open the domain and use public IP of AWS EC2 instance in A record
Go to Route53 in AWS and in DNS management register the domain
https://w3path.com/point-domain-to-aws-ec2-instance/
Wait for 6 hours or so for taking effect

=========================================

How do I transfer a file from local windows to my linux instance in AWS?

Use pscp that comes with putty.
C:\>pscp -P 22 -i "C:\Users\anilb\Desktop\Tech\AWS Keys\MyLinuxBuildEnv.ppk" c:\apache-maven-3.5.0-bin.tar.gz ec2-user@ec2-34-210-13-156.us-west-2.compute.amazonaws.com:/home/ec2-user/maven.tar.gz

c:>pscp -P 22 -i "c:\pem\mykeypair03012020.ppk" target\StockCharts-0.1.0.jar ec2-user@34.209.38.185:/home/ec2-user/StockCharts-0.1.0.jar

=========================================

Download git for windows

https://git-scm.com/download/win

=========================================

How to reset credentials in git

Go to Control Panel > User Accounts > Credential Manager > Windows Credentials. You will see Git credentials in the list

=========================================

How do I install git?

sudo yum install git

=========================================

How do I install maven?

tar xzvf apache-maven-3.5.0-bin.tar.gz

=========================================

How do I install JDK?

tar xzvf jdk-8u121-linux-x64.tar.gz

=========================================

How to remember git credentials?

git config credential-helper store
The creds are  stored in .git-credentials

=========================================

How to install and uninstall tomcat7?

sudo yum install tomcat
sudo yum install tomcat7-webapps tomcat7-docs-webapp tomcat7-admin-webapps
sudo yum remove tomcat

Also see :
https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-7-on-centos-7-via-yum

===========================================

How to install Jenkins?

sudo su
wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo
rpm --import http://pkg.jenkins-ci.org/redhat-stable/jenkins-ci.org.key
yum install jenkins
service jenkins start
chkconfig jenkins on

Open your browser and navigate to http://<Elastic-IP>:8080. You will see jenkins dashboard
============================================

How to install docker?

sudo yum install -y docker
http://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html

===========================================

What is the app URL?

http://localhost:8080/expense/JSP/ReportsList.jsp
http://localhost:8081/expense/expense

Make sure inbound rule has Custom TCP, port 8080, 0.0.0.0/0 added

============================================

where is the tomcat folder?

/etc/tomcat7
/usr/share/tomcat7

==========================================

How to restart tomcat7

sudo service tomcat7 start
sudo service tomcat7 stop
sudo service tomcat7 restart

tomcat7 stop
tomcat7 start

==========================================

What should be in tomcat-users.xml?

	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="tomcat" password="s3cret" roles="manager-gui,manager-script"/>

=========================================

Environment variables

persisted in - /home/ec2-user/.bash_profile
to print these variables use command printenv
to set use export command. E.g. export JAVA_HOME=$HOME/jdk1.8.0_241

=========================================

How to swith to get root access?

sudo su

=========================================

How to deploy/undeploy?

mvn tomcat7:deploy
mvn tomcat7:undeploy
mvn tomcat7:redeploy

=========================================

Dockerization

A great resource is at http://trimc-devops.blogspot.com/2015/03/running-docker-applications-apache.html
Basic useful commands

$sudo service docker restart
$sudo usermod -a -G docker $USER
$exit

$docker build -t anilbhagwat/expense-client .
$docker run -p 8080:8080 anilbhagwat/expense-client
$docker login
$docker push anilbhagwat/expense-client
$docker pull anilbhagwat/expense-client

$aws configure
$aws ecr get-login
$docker login -u AWS -p <password> <url to ECR>

sudo yum install -y docker

sudo service docker restart

docker build -t stockcharts:latest .

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 594359759417.dkr.ecr.us-west-2.amazonaws.com

docker tag stockcharts:latest 594359759417.dkr.ecr.us-west-2.amazonaws.com/stockcharts:latest

docker push 594359759417.dkr.ecr.us-west-2.amazonaws.com/stockcharts:latest

docker run -p 8080:8080 594359759417.dkr.ecr.us-west-2.amazonaws.com/stockcharts:latest

https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes

eksctl create cluster -n stockchartscluster -r us-west-2 -t t3.small

=========================================

How do I check IP addr of my VM?

/sbin/ifconfig

=========================================

How do I check if a process is running on a specific port?

sudo yum install lsof
lsof -i :8080

==========================================

Creating RDS MySQL

On EC2 instance use <sudo yum install mysql>
when creating RDS Mysql, create a default database
After the RDS cluster is created, go to the security group. To the inbound rules add the security group of EC2 instance
To connect from EC2 use, <mysql -h <cluster name> -u user -p <dbname>>

============================================

AWS

https://www.stratoscale.com/blog/cloud/vpc-for-dummies/
https://medium.com/better-programming/turn-your-own-docker-image-into-an-auto-scaling-kubernetes-deployment-using-amazon-eks-bd2ab8ef56cc
https://learnk8s.io/troubleshooting-deployments#:~:text=Pods%20in%20a%20not%20Ready%20state&text=When%20the%20Readiness%20probe%20is,describe%20to%20identify%20the%20error.
https://platform9.com/blog/kubernetes-enterprise-chapter-2-kubernetes-architecture-concepts/
https://www.edureka.co/blog/amazon-eks/#DEMO

aws ec2 start-instances --instance-ids i-04c3655917ca73ef7 --region us-west-2
aws rds start-db-instance --db-instance-identifier database-1

aws ec2 stop-instances --instance-ids i-04c3655917ca73ef7 --region us-west-2
aws rds stop-db-instance --db-instance-identifier database-1

aws ec2 describe-instances --instance-ids i-04c3655917ca73ef7 --region us-west-2
http://34.209.38.185:8080/
