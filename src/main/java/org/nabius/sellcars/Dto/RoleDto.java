package org.nabius.sellcars.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nabius.sellcars.entity.Privilege;
import org.nabius.sellcars.entity.Role;
import org.nabius.sellcars.entity.User;
import org.nabius.sellcars.enums.UserType;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for {@link Role}
 */
@AllArgsConstructor
@Getter
@Setter
public class RoleDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Collection<UserDto> users;
    private final Collection<PrivilegeDto> privileges;

    /**
     * DTO for {@link User}
     */
    @AllArgsConstructor
    @Getter
    public static class UserDto implements Serializable {
        private final Long id;
        @Size(max = 20)
        @NotBlank
        private final String username;
        @Size(max = 80)
        @NotBlank
        private final String fullName;
        @Size(max = 50)
        @Email
        @NotBlank
        private final String email;
        @Size(max = 120)
        @NotBlank
        private final String password;
        private final UserType type;
        private final boolean enabled;
        private final boolean credentialsExpired;
        private final boolean accountLocked;


        /**
         * DTO for {@link Privilege}
         */
        @AllArgsConstructor
        @Getter
        public static class PrivilegeDto implements Serializable {
            private final Long id;
            private final String name;
        }
    }
}