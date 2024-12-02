package org.nabius.sellcars.mappers;

import org.mapstruct.*;
import org.nabius.sellcars.Dto.CurrencyDto;
import org.nabius.sellcars.entity.Currency;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyMapper {
    Currency toEntity(CurrencyDto currencyDto);

    CurrencyDto toDto(Currency currency);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Currency partialUpdate(CurrencyDto currencyDto, @MappingTarget Currency currency);
}