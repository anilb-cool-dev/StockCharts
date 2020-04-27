package StockCharts.Model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Meta Data",
        "Time Series (Daily)"
})
public class StockPriceAPIResponse
{
    @JsonProperty("Meta Data")
    private MetaData metaData;
    @JsonProperty("Time Series (Daily)")
    private TimeSeriesDaily timeSeriesDaily;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Meta Data")
    public MetaData getMetaData() {
        return metaData;
    }

    @JsonProperty("Meta Data")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("Time Series (Daily)")
    public TimeSeriesDaily getTimeSeriesDaily() {
        return timeSeriesDaily;
    }

    @JsonProperty("Time Series (Daily)")
    public void setTimeSeriesDaily(TimeSeriesDaily timeSeriesDaily) {
        this.timeSeriesDaily = timeSeriesDaily;
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
