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

    public List getTickers()
    {
        List<String> tickers = dao.getTickers();
        return tickers;
    }

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

    public void recordPurchase(String ticker, String date)
    {
        List<String> tickers = dao.getTickers();

        if (tickers.contains(ticker))
        {
            dao.recordPurchase(ticker, date);
        }
    }

    public boolean refresh(String ticker)
    {
        boolean response = false;

        RestTemplate restTemplate = new RestTemplate();

        StockPriceAPIResponse stockPriceAPIResponse = restTemplate.getForObject(url, StockPriceAPIResponse.class, ticker);

        if (stockPriceAPIResponse != null && stockPriceAPIResponse.getTimeSeriesDaily() != null)
        {
            for (Map.Entry<String, Object> entry : stockPriceAPIResponse.getTimeSeriesDaily().getAdditionalProperties().entrySet())
            {
                String date = entry.getKey();
                Map map = (Map) entry.getValue();
                String price = (String) map.get("4. close");
                if (dao.priceCount(ticker, date) == 0)
                {
                    dao.setPriceHistory(ticker, date, price);
                }
            }

            response = true;
        }
        return response;
    }

    public boolean refreshAll()
    {
        int count = 0;
        List<String> tickers = dao.getTickers();
        for(String ticker : tickers)
        {
            refresh(ticker);
            count++;
            if (count == 5)
            {
                try
                {
                    Thread.sleep(60000);
                }
                catch(Exception e)
                {}
                count = 0;
            }
        }
        return true;
    }

    public void reset()
    {
        List<String> tickers = dao.getTickers();

        for (String ticker : tickers)
        {

            dao.resetPriceHistory(ticker);
            dao.resetPurchaseHistory(ticker);
        }
    }

    public void reset(String ticker)
    {
        dao.resetPriceHistory(ticker);
        dao.resetPurchaseHistory(ticker);
    }
}
