package org.nabius.sellcars.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link org.nabius.sellcars.entity.Brand}
 */
@AllArgsConstructor
@Getter
public class BrandsDto implements Serializable {
    private final Long id;
    private final String name;
}