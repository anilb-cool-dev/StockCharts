package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

@RestController
public class StockChartsController
{
    static Connection connection = null;

    @RequestMapping("/")
    public String index()
    {
        int retVal = establishConnection();

        if (retVal == 1)
        {
            retVal = retrieveValue();
        }

        return Integer.toString(retVal);
    }

    public static int retrieveValue()
    {
        int retVal = 0, i =0;

        try
        {
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

    public static int establishConnection()
    {
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

        return retVal;
    }
}
