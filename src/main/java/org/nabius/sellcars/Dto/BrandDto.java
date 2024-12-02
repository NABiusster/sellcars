package org.nabius.sellcars.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nabius.sellcars.entity.Brand;
import org.nabius.sellcars.entity.Model;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Brand}
 */
@AllArgsConstructor
@Getter
public class BrandDto implements Serializable {
    private final Long id;
    private final String name;
    private final Set<ModelDto> models;

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