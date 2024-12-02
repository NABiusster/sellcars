package org.nabius.sellcars.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.nabius.sellcars.entity.Privilege;

import java.util.Collection;

/**
 * DTO for {@link Privilege}
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PrivilegeDto {
    private final Long id;
    private final String name;
    private final Collection<RoleDto> roles;
}