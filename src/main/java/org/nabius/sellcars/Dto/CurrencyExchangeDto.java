package org.nabius.sellcars.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nabius.sellcars.Dto.CurrencyDto;
import org.nabius.sellcars.entity.CurrencyExchange;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link CurrencyExchange}
 */
@AllArgsConstructor
@Getter
public class CurrencyExchangeDto implements Serializable {
    private final int id;
    private final Date date;
    private final CurrencyDto currency;
    private final double buyAmount;
    private final double sellAmount;
}