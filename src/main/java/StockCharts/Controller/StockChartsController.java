package StockCharts.Controller;

import StockCharts.Model.PurchaseHistory;
import StockCharts.Service.StockChartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            Date date1 = df.parse(date);
            String date2 = df.format(date1);
            service.recordPurchase(ticker, date2);
        }
        catch(Exception e)
        {}

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

    @RequestMapping("/refreshAll")
    public ResponseEntity<Boolean> refresh()
    {
        boolean status = service.refreshAll();

        return ResponseEntity.ok(Boolean.valueOf(status));
    }

    @RequestMapping("/resetAll")
    public ResponseEntity<Boolean> reset()
    {
        service.reset();
        return ResponseEntity.ok(true);
    }

    @RequestMapping("/reset/{ticker}")
    public ResponseEntity<Boolean> reset(@PathVariable String ticker)
    {
        service.reset(ticker);
        return ResponseEntity.ok(true);
    }
}
