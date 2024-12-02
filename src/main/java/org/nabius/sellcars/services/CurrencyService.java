package org.nabius.sellcars.services;

import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.entity.Currency;
import org.nabius.sellcars.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public Currency findCurrencyByName(String currencyName) {
        return currencyRepository.findByName(currencyName).orElseThrow(
                () -> new IllegalArgumentException("Currency not found")
        );
    }
}
