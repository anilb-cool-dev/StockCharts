package StockCharts.Service;

import StockCharts.Model.Price;
import StockCharts.Model.StockPriceAPIResponse;
import StockCharts.dao.StockChartsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StockChartsService
{
    @Autowired
    private StockChartsDAO dao;

    static String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={ticker}&apikey=8AA2U02KXGQDXKB2";

    public List getPrices(String ticker)
    {
        List<Price> prices = dao.getPriceHistory(ticker);
        return prices;
    }

    public List getPurchases(String ticker)
    {
        List<String> purchases = dao.getPurchaseHistory(ticker);
        return purchases;
    }

    public boolean refresh(String ticker)
    {
        boolean response = false;

        RestTemplate restTemplate = new RestTemplate();

        StockPriceAPIResponse stockPriceAPIResponse = restTemplate.getForObject(url, StockPriceAPIResponse.class, ticker);

        if (stockPriceAPIResponse != null)
        {
            for (Map.Entry<String, Object> entry : stockPriceAPIResponse.getTimeSeriesDaily().getAdditionalProperties().entrySet())
            {
                String date = entry.getKey();
                Map map = (Map) entry.getValue();
                String price = (String) map.get("4. close");
                dao.setPriceHistory(ticker, date, price);
            }

            response = true;
        }
        return response;
    }
}
