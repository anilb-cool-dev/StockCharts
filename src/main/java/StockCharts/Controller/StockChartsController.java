package StockCharts.Controller;

import StockCharts.Service.StockChartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/refresh/{ticker}")
    public ResponseEntity<Boolean> refresh(@PathVariable String ticker)
    {
        boolean status = service.refresh(ticker);

        return ResponseEntity.ok(Boolean.valueOf(status));
    }
}
