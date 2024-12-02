package org.nabius.sellcars.mappers;

import org.mapstruct.*;
import org.nabius.sellcars.Dto.UserDto;
import org.nabius.sellcars.entity.User;

@Mapper(uses = {RoleMapper.class}
        , componentModel = MappingConstants.ComponentModel.SPRING
//        ,
//        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto entityToDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    User toEntity(UserDto userDto);

    @AfterMapping
    default void linkCars(@MappingTarget User user) {
        user.getCars().forEach(car -> car.setUser(user));
    }
}