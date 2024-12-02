package org.nabius.sellcars.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.entity.Role;
import org.nabius.sellcars.Dto.RoleDto;
import org.nabius.sellcars.mappers.RoleMapper;
import org.nabius.sellcars.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
    }

    public Role findRoleById(@Valid Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new RuntimeException("Role not found")
        );
    }

    public Collection<RoleDto> findAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.dtoToEntity(roleDto);
        return roleMapper.entityToDto(roleRepository.save(role));
    }

    public void deleteRole(@Valid Long roleId) {
        roleRepository.deleteById(roleId);
    }

    public RoleDto findRoleDtoById(@Valid Long roleId) {
        return roleMapper.entityToDto(findRoleById(roleId));
    }
}
