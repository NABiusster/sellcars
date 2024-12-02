package org.nabius.sellcars.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.Auth.SignUpRequestDto;
import org.nabius.sellcars.Dto.UserDto;
import org.nabius.sellcars.Dto.RoleDto;
import org.nabius.sellcars.entity.Role;
import org.nabius.sellcars.entity.User;
import org.nabius.sellcars.enums.UserType;
import org.nabius.sellcars.mappers.RoleMapper;
import org.nabius.sellcars.mappers.UserMapper;
import org.nabius.sellcars.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;


    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }

    @Transactional
    public UserDto createUser(@Valid SignUpRequestDto signUpRequestDto) {
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        User user = new User();
        user.setUsername(signUpRequestDto.getUsername());
        user.setFullName(signUpRequestDto.getFullName());
        user.setType(UserType.BASIC);
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(password);
        user.setRoles(List.of(roleService.findRoleByName("ROLE_USER")));
        userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    @Transactional
    public Optional<UserDto> partialUpdateUser(Long userId, UserDto userDto) {
        return this.userRepository.findById(userId).map(user -> this.userMapper.partialUpdate(userDto, user))
                .map(this.userMapper::entityToDto);
    }

    public void deleteUser(Long userId) {
        if (!this.userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        this.userRepository.deleteById(userId);
    }

    public Collection<RoleDto> getRolesForUser(@Valid @PathVariable Long userId) {
        return this.findUserById(userId).getRoles().stream().map(roleMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public Collection<RoleDto> addRoleForUserId(@Valid Long userId, @Valid Long roleId) {
        User user = this.findUserById(userId);
        Collection<Role> roles = user.getRoles();
        roles.add(roleService.findRoleById(roleId));
        user.setRoles(roles);
        return this.userRepository.save(user).getRoles().stream().map(roleMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public Collection<RoleDto> deleteRoleForUserId(@Valid Long userId, @Valid Long roleId) {
        User user = this.findUserById(userId);
        user.getRoles().remove(roleService.findRoleById(roleId));
        return this.userRepository.save(user).getRoles().stream().map(roleMapper::entityToDto).collect(Collectors.toList());
    }

    public Collection<UserDto> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(userMapper::entityToDto)
                .toList();
    }
}