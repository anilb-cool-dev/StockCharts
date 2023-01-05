package StockCharts.Controller;

import StockCharts.Model.PurchaseHistory;
import StockCharts.Service.StockChartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


@RestController
public class StockChartsController
{
    @Autowired
    private StockChartsService service;

    @ApiIgnore
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value="/abcd", method=RequestMethod.GET)
    public String abcd()
    {
        String response = "";
        try {
            throw new Exception("Exception has occured....");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            LOG.error("Exception - " + stackTrace);
            response = stackTrace;
        }

        return response;
    }

    @RequestMapping(value="/tickers", method=RequestMethod.GET)
    public ResponseEntity<ArrayList> tickers()
    {
        ArrayList tickers = (ArrayList)service.getTickers();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(tickers, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/priceHistory/{ticker}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList> priceHistory(@PathVariable String ticker)
    {
        ArrayList prices = (ArrayList)service.getPrices(ticker);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(prices, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/purchaseHistory/{ticker}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList> purchaseHistory(@PathVariable String ticker)
    {
        ArrayList purchases = (ArrayList)service.getPurchases(ticker);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        return new ResponseEntity<>(purchases, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/purchaseHistory/{ticker}", method=RequestMethod.POST)
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

    @RequestMapping(value="/refresh/{ticker}", method=RequestMethod.GET)
    public ResponseEntity<Boolean> refresh(@PathVariable String ticker)
    {
        boolean status = service.refresh(ticker);

        return ResponseEntity.ok(Boolean.valueOf(status));
    }

    @RequestMapping(value="/refreshAll", method=RequestMethod.GET)
    public ResponseEntity<Boolean> refresh()
    {
        boolean status = service.refreshAll();

        return ResponseEntity.ok(Boolean.valueOf(status));
    }

    @RequestMapping(value="/resetAll", method=RequestMethod.GET)
    public ResponseEntity<Boolean> reset()
    {
        service.reset();
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value="/reset/{ticker}", method=RequestMethod.GET)
    public ResponseEntity<Boolean> reset(@PathVariable String ticker)
    {
        service.reset(ticker);
        return ResponseEntity.ok(true);
    }
}
