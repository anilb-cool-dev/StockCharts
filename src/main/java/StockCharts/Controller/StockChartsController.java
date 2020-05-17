package StockCharts.Controller;

import StockCharts.Model.PurchaseHistory;
import StockCharts.Service.StockChartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/priceHistory/{ticker}")
    public ResponseEntity<ArrayList> priceHistory(@PathVariable String ticker)
    {
        ArrayList prices = (ArrayList)service.getPrices(ticker);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(prices, headers, HttpStatus.OK);
    }

    @RequestMapping("/purchaseHistory/{ticker}")
    public ResponseEntity<ArrayList> purchaseHistory(@PathVariable String ticker)
    {
        ArrayList purchases = (ArrayList)service.getPurchases(ticker);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(purchases, headers, HttpStatus.OK);
    }

    @PostMapping("/purchaseHistory/{ticker}")
    public ResponseEntity<PurchaseHistory> newPurchaseHistory(@PathVariable String ticker, @RequestBody PurchaseHistory newPurchaseHistory)
    {
        String date = newPurchaseHistory.getDate();

        service.recordPurchase(ticker, date);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping("/refresh/{ticker}")
    public ResponseEntity<Boolean> refresh(@PathVariable String ticker)
    {
        boolean status = service.refresh(ticker);

        return ResponseEntity.ok(Boolean.valueOf(status));
    }
}
