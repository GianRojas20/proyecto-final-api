package com.cibertec.proyectofinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private boolean ok;
    private String message;
    private String username;
    private String rol;
}