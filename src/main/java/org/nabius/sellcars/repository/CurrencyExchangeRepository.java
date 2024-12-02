package org.nabius.sellcars.repository;

import org.nabius.sellcars.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {

    boolean existsByDateAndCurrency_Code(Date date, Integer code);

    Collection<CurrencyExchange> findAllByDate(Date date);
}