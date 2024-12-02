package org.nabius.sellcars.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nabius.sellcars.entity.Brand;
import org.nabius.sellcars.entity.Car;
import org.nabius.sellcars.entity.Model;
import org.nabius.sellcars.entity.User;
import org.nabius.sellcars.enums.UserType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Car}
 */
@AllArgsConstructor
@Getter
public class CarDto implements Serializable {
    private final Long id;
    private final BigDecimal price;
    private final CurrencyDto currency;
    private final UserDto user;
    private final BrandDto brand;
    private final ModelDto model;
    

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
        private final UserType type;
        private final boolean enabled;
        private final boolean credentialsExpired;
        private final boolean accountLocked;
    }

    /**
     * DTO for {@link Brand}
     */
    @AllArgsConstructor
    @Getter
    public static class BrandDto implements Serializable {
        private final Long id;
        private final String name;
    }

    /**
     * DTO for {@link Model}
     */
    @AllArgsConstructor
    @Getter
    public static class ModelDto implements Serializable {
        private final int id;
        private final String name;
    }
}