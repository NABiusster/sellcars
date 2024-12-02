package org.nabius.sellcars.mappers;

import org.mapstruct.*;
import org.nabius.sellcars.Dto.CurrencyExchangeDto;
import org.nabius.sellcars.entity.CurrencyExchange;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyExchangeMapper {
    CurrencyExchange DtoToEntity(CurrencyExchangeDto privatCurrencyExchangeDto);

    CurrencyExchangeDto entityToDto(CurrencyExchange currencyExchange);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurrencyExchange partialUpdate(CurrencyExchangeDto privatCurrencyExchangeDto, @MappingTarget CurrencyExchange currencyExchange);
}