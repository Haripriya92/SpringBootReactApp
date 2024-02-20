package org.launchcode.givewise.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactFormDTO {

    // Getters and setters
    private String name;
    private String email;
    private String message;

    // Constructors
    public ContactFormDTO() {
    }

    public ContactFormDTO(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}

