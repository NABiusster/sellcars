package org.nabius.sellcars.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.RoleDto;
import org.nabius.sellcars.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Collection<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(this.roleService.findAllRoles());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRole(@Valid @PathVariable Long roleId) {

        return ResponseEntity.ok(roleService.findRoleDtoById(roleId));
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.createRole(roleDto));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@Valid @PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

}
