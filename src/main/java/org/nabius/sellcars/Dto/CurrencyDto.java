package org.nabius.sellcars.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link org.nabius.sellcars.entity.Currency}
 */
@AllArgsConstructor
@Getter
public class CurrencyDto implements Serializable {
    private final Integer code;
    private final String name;
    private final String symbol;
}