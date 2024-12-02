package org.nabius.sellcars.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.BrandDto;
import org.nabius.sellcars.Dto.BrandsDto;
import org.nabius.sellcars.mappers.BrandMapper;
import org.nabius.sellcars.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandsService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandsDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::toBrandsDto)
                .collect(Collectors.toList());
    }

    public BrandDto getBrandById(@Valid Long brandId) {
        return brandRepository.findById(brandId).map(brandMapper::toBrandDto)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid brandId: " + brandId));
    }
}
