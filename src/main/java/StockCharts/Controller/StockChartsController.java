package StockCharts.Controller;

import StockCharts.Service.StockChartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

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
    public ResponseEntity<ArrayList> priceHistory()
    {
        ArrayList prices = service.getPrices();
        return ResponseEntity.ok(prices);
    }

    @RequestMapping("/purchaseHistory")
    public ResponseEntity<ArrayList> purchaseHistory()
    {
        ArrayList purchases = service.getPurchases();
        return ResponseEntity.ok(purchases);
    }
}
