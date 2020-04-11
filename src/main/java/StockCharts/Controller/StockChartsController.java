package StockCharts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@RestController
public class StockChartsController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/priceHistory")
    public Price[] priceHistory() {
        Price prices[] = PriceUtil.getPrices();
        return prices;
    }
}
