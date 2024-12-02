package org.nabius.sellcars.controllers;

import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.CurrencyExchangeDto;
import org.nabius.sellcars.services.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/exchange")
    public ResponseEntity<Collection<CurrencyExchangeDto>> getCurrentCurrencyExchanges(){
        return ResponseEntity.ok(currencyExchangeService.getCurrentCurrencyExchange());
    }


}
