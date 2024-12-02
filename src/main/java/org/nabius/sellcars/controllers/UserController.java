package org.nabius.sellcars.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.UserDto;
import org.nabius.sellcars.Dto.RoleDto;
import org.nabius.sellcars.services.AuthService;
import org.nabius.sellcars.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Collection<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PatchMapping("/{userId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<UserDto> partialUpdateUser(@Valid @PathVariable Long userId,
                                                     @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.of(userService.partialUpdateUser(userId, userDto));
    }

    @PutMapping("/{userId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<UserDto> UpdateUser(@Valid @PathVariable Long userId,
                                              @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.of(userService.partialUpdateUser(userId, userDto));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> authenticatedUser() {
        return ResponseEntity.ok(authService.getUserDetailsCurrentUser());
    }

    @GetMapping("/{id}/roles")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Collection<RoleDto>> findAllRolesForUserId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(userService.getRolesForUser(id));
    }

    @PostMapping("{userId}/roles/{roleId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Collection<RoleDto>> addRoleForUserId(@Valid @PathVariable Long userId, @Valid @PathVariable Long roleId) {
        return ResponseEntity.ok(userService.addRoleForUserId(userId, roleId));
    }

    @DeleteMapping("{userId}/roles/{roleId}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Collection<RoleDto>> deleteRoleForUserId(@Valid @PathVariable Long userId, @Valid @PathVariable Long roleId) {

        return ResponseEntity.ok(userService.deleteRoleForUserId(userId, roleId));
    }
}