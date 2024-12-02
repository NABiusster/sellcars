package org.nabius.sellcars.repository;

import org.nabius.sellcars.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
  Optional<Currency> findByName(String name);

  Optional<Currency> findByCode(int code);
}