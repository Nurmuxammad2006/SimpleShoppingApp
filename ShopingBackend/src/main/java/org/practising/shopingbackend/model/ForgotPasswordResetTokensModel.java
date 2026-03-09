package org.practising.shopingbackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reset_token")
public class ForgotPasswordResetTokensModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthModel user;

    private LocalDateTime expireDate;

    private Boolean isUsed;

    // CONSTRUCTORS
    public ForgotPasswordResetTokensModel() {}

    public ForgotPasswordResetTokensModel(AuthModel authModel) {
        this.token = UUID.randomUUID().toString();
        this.user = authModel;
        this.expireDate = LocalDateTime.now().plusMinutes(15);
        this.isUsed = false;
    }

    // GETTERS
    public int getId() { return id; }
    public String getToken() { return token; }
    public AuthModel getUser() { return user; }
    public LocalDateTime getExpireDate() { return expireDate; }
    public Boolean getIsUsed() { return isUsed; }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setToken(String token) { this.token = token; }
    public void setUser(AuthModel user) { this.user = user; }
    public void setExpireDate(LocalDateTime expireDate) { this.expireDate = expireDate; }
    public void setIsUsed(Boolean isUsed) { this.isUsed = isUsed; }
}