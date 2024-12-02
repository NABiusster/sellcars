package org.nabius.sellcars.Dto.Auth;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String username;
    private String fullName;
    private String password;
    private String email;

}

