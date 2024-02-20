package org.launchcode.givewise.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
}
