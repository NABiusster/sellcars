package org.nabius.sellcars.Dto;

import lombok.Data;

@Data
public class PrivatCurrencyExchangeDto {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
