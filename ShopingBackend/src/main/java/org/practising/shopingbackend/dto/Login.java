package org.practising.shopingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Login {
    private String email;
    private String password;

    //GETTERS
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //SETTERS
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
