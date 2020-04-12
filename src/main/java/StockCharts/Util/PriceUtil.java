package StockCharts.Util;

import StockCharts.Model.Price;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class PriceUtil {
    public static Price[] getPrices()
    {
        Price price;
        Price[] Prices = new Price[2];

        price = new Price();
        price.date = new Date();
        price.ticker = "GOOG";
        price.price = 23.19;
        Prices[0] = price;

        price = new Price();
        price.date = new Date();
        price.ticker = "MSFT";
        price.price = 100.01;
        Prices[1] = price;

        return Prices;
    }

    public static int retrieveValue()
    {
        int retVal = 0, i =0;

        try
        {
            Connection connection = ConnectionUtil.establishConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select key1 from table1");
            while (rs.next())
            {
                i = rs.getInt("key1");
            }

            retVal = i;

            rs.close();
            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
            retVal = -1;
        }

        return retVal;
    }
}
