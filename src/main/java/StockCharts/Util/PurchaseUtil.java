package StockCharts.Util;

import StockCharts.Model.Purchase;

import java.util.Date;

public class PurchaseUtil {
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
