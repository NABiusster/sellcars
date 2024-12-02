package org.nabius.sellcars.services;

import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.PrivatCurrencyExchangeDto;
import org.nabius.sellcars.entity.Currency;
import org.nabius.sellcars.entity.CurrencyExchange;
import org.nabius.sellcars.Dto.CurrencyExchangeDto;
import org.nabius.sellcars.mappers.CurrencyExchangeMapper;
import org.nabius.sellcars.repository.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeMapper currencyExchangeMapper;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyService currencyService;

    public void loadCurrencyExchange(PrivatCurrencyExchangeDto privatCurrencyExchange) {
        Currency currency=currencyService.findCurrencyByName(privatCurrencyExchange.getCcy());
        if (!currencyExchangeRepository.existsByDateAndCurrency_Code(new Date(), currency.getCode()))
        {
            CurrencyExchange currencyExchange = new CurrencyExchange();
            currencyExchange.setCurrency(currency);
            currencyExchange.setDate(new Date());
            currencyExchange.setBuyAmount(Double.parseDouble(privatCurrencyExchange.getBuy()));
            currencyExchange.setSellAmount(Double.parseDouble(privatCurrencyExchange.getSale()));
            currencyExchangeRepository.save(currencyExchange);;
        }
    }

    public Collection<CurrencyExchangeDto> getCurrentCurrencyExchange() {
       return currencyExchangeRepository.findAllByDate(new Date()).stream().map(currencyExchangeMapper::entityToDto).collect(Collectors.toList());
    }
}
