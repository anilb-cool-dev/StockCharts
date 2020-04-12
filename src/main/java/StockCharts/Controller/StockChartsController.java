package StockCharts.Controller;

import StockCharts.Model.Price;
import StockCharts.Util.PriceUtil;

import StockCharts.Model.Purchase;
import StockCharts.Util.PurchaseUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StockChartsController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/priceHistory")
    public ResponseEntity<Price[]> priceHistory() {
        Price[] prices = PriceUtil.getPrices();
        return ResponseEntity.ok(prices);
    }

    @RequestMapping("/purchaseHistory")
    public ResponseEntity<Purchase[]> purchaseHistory() {
        Purchase[] purchases = PurchaseUtil.getPurchases();
        return ResponseEntity.ok(purchases);
    }
}