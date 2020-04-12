package StockCharts.Service;

import StockCharts.Model.Price;
import StockCharts.Model.Purchase;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockChartsService {
    public static Price[] getPrices()
    {
        Price price;
        Price[] Prices = new Price[2];

        price = new Price();
        price.date = new Date();
        price.ticker = "GOOG";
        price.price = 23.19;
        Prices[0] = price;

        price = new Price();
        price.date = new Date();
        price.ticker = "MSFT";
        price.price = 100.01;
        Prices[1] = price;

        return Prices;
    }

    public static Purchase[] getPurchases()
    {
        Purchase purchase;
        Purchase[] purchases = new Purchase[1];

        purchase = new Purchase();
        purchase.date = new Date();
        purchase.ticker = "GOOG";
        purchases[0] = purchase;

        return purchases;
    }
}
