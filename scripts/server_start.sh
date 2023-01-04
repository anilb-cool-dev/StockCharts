#!/usr/bin/env bash
cd /home/ec2-user/server
sudo java -jar *.jar > /home/ec2-user/java/stockcharts/springboot.log 2> /home/ec2-user/java/stockcharts/springboot.log < /home/ec2-user/java/stockcharts/springboot.log &