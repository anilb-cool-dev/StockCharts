# StockCharts

StockCharts is a tool to show purchase price of a stock relative to its price history. With StockCharts, you can get a sense of where on the price history curve did you end up buying it. Did you often buy it when the stock was low or did you end up buying it when it was always high or maybe there was no real corelation. But, seeing it visually helps to figure out if you did ok. So, e.g. see the chart below

It looks like I bought stocks of AMAZON always when it was near low. This puts a smile on my face

But look at my purchasing history for MSFT. What was I thinking?

This used to be a feature in MSN Investor long time ago when it was alive. I used to use it a lot & it appears a lot of other people also used it. But, MS decided to sunset that feature of MSN investor & I couldn't find this feature anywhere else, so I decided to create it myself. It also gave me opportunity to keep my coding chops in shape. 

I am using JAVA springboot for my business logic tier. The backend data storage is MySQL. I am using google charts for charting and jquery for UI rendering. I am deploying it to AWS. I added Redis (Elasticache) to cache the ticker symbols. Although, is is miniscule amount of data, it will come in handy if/when I decide to add multi-user support & open it to others to use.

Here are some of the aspirational TODO items. These are primariliy because I want to play around with these

1. SSL
2. AWS secrets
4. Cron job to update price history
5. Multi user support
6. Unit tests with mocks
7. Integration tests using RestAssured
8. Load test using gatling and jmeter
9. Mobile app

Access using : http://www.mystockpurchases.com:8080/StockCharts.html
