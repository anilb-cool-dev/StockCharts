package StockCharts.Model;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "historicalPrice"
})
public class TimeSeriesDaily {

    @JsonProperty("historicalPrice")
    private HistoricalPrice historicalPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("historicalPrice")
    public HistoricalPrice getHistoricalPrice() {
        return historicalPrice;
    }

    @JsonProperty("historicalPrice")
    public void setHistoricalPrice(HistoricalPrice historicalPrice) {
        this.historicalPrice = historicalPrice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}