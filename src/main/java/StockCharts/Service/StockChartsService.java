package StockCharts.Service;

import StockCharts.Model.HistoricalPrice;
import StockCharts.Model.Price;
import StockCharts.Model.StockPriceAPIResponse;
import StockCharts.Util.ConnectionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@Service
public class StockChartsService
{
    public static ArrayList getPrices(String ticker)
    {
        ArrayList prices = new ArrayList();

        try
        {
            Connection connection = ConnectionUtil.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select date, price from price_history where ticker = '" + ticker + "'");

            while (rs.next())
            {
                Price price = new Price();

                price.date = rs.getDate("date");
                price.price = rs.getDouble("price");

                prices.add(price);
            }

            rs.close();
            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
        }

        return prices;
    }

    public static ArrayList getPurchases(String ticker)
    {
        ArrayList purchases = new ArrayList();

        try
        {
            Connection connection = ConnectionUtil.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select date from purchase_history where ticker = '" + ticker + "'");

            while (rs.next())
            {
                Date date = rs.getDate("date");
                purchases.add(date);
            }

            rs.close();
            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
        }

        return purchases;
    }

    public boolean refresh(String ticker)
    {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={ticker}&apikey=8AA2U02KXGQDXKB2";

        RestTemplate restTemplate = new RestTemplate();

        StockPriceAPIResponse stockPriceAPIResponse = restTemplate.getForObject(url, StockPriceAPIResponse.class, ticker);

        //stockPriceAPIResponse.getTimeSeriesDaily().getAdditionalProperties().forEach(<n> -> );

        return false;
    }
}
