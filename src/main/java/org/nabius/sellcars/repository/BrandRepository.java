package org.nabius.sellcars.repository;

import org.nabius.sellcars.Dto.BrandDto;
import org.nabius.sellcars.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandById(Long id);
}