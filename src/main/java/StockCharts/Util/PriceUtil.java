package StockCharts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class PriceUtil {
    public static Price[] getPrices()
    {
        Price price = new Price();
        price.date = new Date();
        price.ticker = "GOOG";
        price.price = 23.19;

        Price[] Prices = new Price[1];
        Prices[0] = price;

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
