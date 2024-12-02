package org.nabius.sellcars.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.BrandDto;
import org.nabius.sellcars.Dto.BrandsDto;
import org.nabius.sellcars.entity.Brand;
import org.nabius.sellcars.services.BrandsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandsController {
    private final BrandsService brandsService;

    @GetMapping
    public ResponseEntity<List<BrandsDto>> getAllBrands() {
        return ResponseEntity.ok(brandsService.getAllBrands());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDto> getBrandById(@Valid @PathVariable Long brandId){
        return ResponseEntity.ok(brandsService.getBrandById(brandId));
    }

}
