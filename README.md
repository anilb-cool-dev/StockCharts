# StockCharts

StockCharts is a tool to show purchase price of a stock relative to its price history. With StockCharts, you can get a sense of where on the price history curve did you end up buying it. Did you often buy it when the stock was low or did you end up buying it when it was always high or maybe there was no real corelation. But, seeing it visually helps to figure out if you did ok. So, e.g. see the chart below

It looks like I bought units of VNQ always when it was near low. This makes me feel alright since most of my purchases are at relatively lower price

<img width="708" alt="image" src="https://user-images.githubusercontent.com/17423724/152484321-5dca79e5-c63c-4d10-8fc1-0b8a4531e78f.png">

But look at my purchasing history for ARKF. What was I thinking?

<img width="701" alt="image" src="https://user-images.githubusercontent.com/17423724/152483450-c5420d33-f66d-4ad9-b69d-76908b9358d9.png">

This used to be a feature in MSN Investor long time ago when it was alive. I used to use it a lot & it appears a lot of other people also used it. But, MS decided to sunset that feature of MSN investor & I couldn't find this feature anywhere else, so I decided to create it myself. It also gave me opportunity to keep my coding chops in shape. 

I am using JAVA springboot for my business logic tier. The backend data storage is MySQL. I am using google charts for charting and jquery for UI rendering. I am deploying it to AWS. I added Redis (Elasticache) to cache the ticker symbols. Although, is is miniscule amount of data, it will come in handy if/when I decide to add multi-user support & open it to others to use.

# Architecture block diagram

# TODO
Here are some of the aspirational TODO items. These are primariliy because I want to play around with these

1. SSL
2. Use github actions for CI/CD
3. AWS secrets
4. Cron job to update price history
5. Multi user support
6. Unit tests with mocks
7. Integration tests using RestAssured
8. Load test using gatling and jmeter
9. Mobile app

Access using : http://www.mystockpurchases.com:8080/StockCharts.html
