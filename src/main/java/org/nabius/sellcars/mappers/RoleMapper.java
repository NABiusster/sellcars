package org.nabius.sellcars.mappers;

import org.mapstruct.*;
import org.nabius.sellcars.Dto.RoleDto;
import org.nabius.sellcars.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(uses = UserMapper.class,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoleMapper {
    Role dtoToEntity(RoleDto roleDto);

    RoleDto entityToDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);

    default Collection<Role> roleDtoListToRoleCollection(List<RoleDto> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Collection<Role> collection = new ArrayList<>(list.size());
        for (RoleDto roleDto : list) {
            collection.add(dtoToEntity(roleDto));
        }
        return collection;
    }
}