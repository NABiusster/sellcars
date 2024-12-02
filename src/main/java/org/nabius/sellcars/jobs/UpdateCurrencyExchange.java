package org.nabius.sellcars.jobs;

import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.PrivatCurrencyExchangeDto;
import org.nabius.sellcars.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UpdateCurrencyExchange {
    @Value("${currency-exchange.url}")
    private String url;
    private final CurrencyExchangeService currencyExchangeService;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void updateCurrencyExchange (){
        RestTemplate restTemplate = new RestTemplate();
        PrivatCurrencyExchangeDto[] currencies = restTemplate.getForObject(url, PrivatCurrencyExchangeDto[].class);
        assert currencies != null;
        for (PrivatCurrencyExchangeDto currency : currencies) {
            currencyExchangeService.loadCurrencyExchange(currency);

        }



    }

}
