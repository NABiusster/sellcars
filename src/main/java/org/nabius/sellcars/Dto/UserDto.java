package org.nabius.sellcars.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nabius.sellcars.enums.UserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * DTO for {@link org.nabius.sellcars.entity.User}
 */
@AllArgsConstructor
@Getter
public class UserDto implements Serializable {
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
    private final Collection<RoleDto> roles;
    private final List<CarDto> cars;

    /**
     * DTO for {@link org.nabius.sellcars.entity.Role}
     */
    @AllArgsConstructor
    @Getter
    public static class RoleDto implements Serializable {
        private final Long id;
        private final String name;
        private final String description;
    }

    /**
     * DTO for {@link org.nabius.sellcars.entity.Car}
     */
    @AllArgsConstructor
    @Getter
    public static class CarDto implements Serializable {
        private final Long id;
        private final BigDecimal price;
        private final CurrencyDto currency;
        private final BrandDto brand;

        /**
         * DTO for {@link org.nabius.sellcars.entity.Currency}
         */
        @AllArgsConstructor
        @Getter
        public static class CurrencyDto implements Serializable {
            private final Integer code;
            private final String name;
            private final String symbol;
        }

        /**
         * DTO for {@link org.nabius.sellcars.entity.Brand}
         */
        @AllArgsConstructor
        @Getter
        public static class BrandDto implements Serializable {
            private final Long id;
            private final String name;
        }
    }
}