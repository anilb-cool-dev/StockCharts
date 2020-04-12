package StockCharts.Controller;

import StockCharts.Model.Price;
import StockCharts.Service.StockChartsService;

import StockCharts.Model.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StockChartsController
{
    @Autowired
    private StockChartsService service;

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/priceHistory")
    public ResponseEntity<Price[]> priceHistory() {
        Price[] prices = service.getPrices();
        return ResponseEntity.ok(prices);
    }

    @RequestMapping("/purchaseHistory")
    public ResponseEntity<Purchase[]> purchaseHistory() {
        Purchase[] purchases = service.getPurchases();
        return ResponseEntity.ok(purchases);
    }
}
