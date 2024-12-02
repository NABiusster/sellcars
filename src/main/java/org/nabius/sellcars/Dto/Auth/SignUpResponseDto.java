package org.nabius.sellcars.Dto.Auth;

import lombok.Builder;
import lombok.Data;
import org.nabius.sellcars.entity.Role;

import java.util.Collection;

@Data
@Builder
public class SignUpResponseDto {

    private Long id;
    private String username;
    private String email;
    private Collection<Role> roles;

}