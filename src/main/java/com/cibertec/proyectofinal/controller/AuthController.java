package com.cibertec.proyectofinal.controller;

import com.cibertec.proyectofinal.model.LoginRequest;
import com.cibertec.proyectofinal.model.LoginResponse;
import com.cibertec.proyectofinal.model.Usuario;
import com.cibertec.proyectofinal.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        Usuario u = usuarioRepository.findByUsername(request.getUsername()).orElse(null);

        if (u == null || Boolean.FALSE.equals(u.getActivo())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Usuario no existe o está inactivo", null, null));
        }

        boolean ok = passwordEncoder.matches(request.getPassword(), u.getPassword());

        if (!ok) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Contraseña incorrecta", null, null));
        }

        return ResponseEntity.ok(new LoginResponse(true, "Login correcto", u.getUsername(), u.getRol()));
    }
}