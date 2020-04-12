package StockCharts.Service;

import StockCharts.Model.Price;
import StockCharts.Model.Purchase;
import StockCharts.Util.ConnectionUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@Service
public class StockChartsService {
    public static ArrayList getPrices()
    {
        ArrayList prices = new ArrayList();

        try
        {
            Connection connection = ConnectionUtil.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select ticker, date, price from price_history");

            while (rs.next())
            {
                Price price = new Price();

                price.ticker = rs.getString("ticker");
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

    public static ArrayList getPurchases()
    {
        ArrayList purchases = new ArrayList();

        try
        {
            Connection connection = ConnectionUtil.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select ticker, date from purchase_history");

            while (rs.next())
            {
                Purchase purchase = new Purchase();

                purchase.ticker = rs.getString("ticker");
                purchase.date = rs.getDate("date");

                purchases.add(purchase);
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
}
