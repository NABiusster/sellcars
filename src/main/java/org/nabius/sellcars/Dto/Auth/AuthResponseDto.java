package org.nabius.sellcars.Dto.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    private String refreshToken;
    private String accessToken;

}