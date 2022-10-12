mkdir java
mkdir java/stockcharts
cd java/stockcharts
git clone https://github.com/anilb-cool-dev/StockCharts .
mvn package
export dbinstance=$1
export dbname=$2
export dbuserid=$3
export dbpassword=$4
