package org.launchcode.givewise.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Schema(example = "user")
    @NotBlank
    private String email;

    @Schema(example = "user")
    @NotBlank
    private String password;
}
