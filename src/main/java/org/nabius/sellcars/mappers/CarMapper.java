package org.nabius.sellcars.mappers;

import org.mapstruct.*;
import org.nabius.sellcars.Dto.CarDto;
import org.nabius.sellcars.entity.Car;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car mapToEntity(CarDto carDto);

    CarDto mapToDto(Car car);

    Car update(CarDto carDto, @MappingTarget Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDto carDto, @MappingTarget Car car);
}