package pl.mmarkowicz.interview.web.response;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RealEstateAveragePriceStatsResponse {
    private BigDecimal avgValue;
}
