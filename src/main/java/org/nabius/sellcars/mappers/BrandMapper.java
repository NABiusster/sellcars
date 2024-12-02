package org.nabius.sellcars.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.nabius.sellcars.Dto.BrandDto;
import org.nabius.sellcars.Dto.BrandsDto;
import org.nabius.sellcars.entity.Brand;

@Mapper(
//        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {
    BrandDto toBrandDto(Brand brand);
    BrandsDto toBrandsDto(Brand brand);

    Brand toEntity(BrandDto brandDto);
}