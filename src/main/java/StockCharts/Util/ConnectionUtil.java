package StockCharts.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection establishConnection()
    {
        Connection connection = null;
        int retVal;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            retVal = -1;
        }

        try
        {
            connection = DriverManager.
                    getConnection("jdbc:mysql://database-1.cdtxxj0td5de.us-west-2.rds.amazonaws.com:3306/MyDB?user=admin123&password=admin123");
        } catch (SQLException e)
        {
            retVal = -2;
        }

        if (connection != null)
        {
            retVal = 1;
        } else {
            retVal = -3;
        }

        return connection;
    }
}
