package StockCharts.dao;

import StockCharts.Model.Price;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockChartsDAO
{
    List<Price> getPriceHistory(@Param("ticker") String ticker);

    List<String> getPurchaseHistory(@Param("ticker") String ticker);

    void setPriceHistory(@Param("ticker") String ticker, @Param("date") String date, @Param("price") String price);
}
