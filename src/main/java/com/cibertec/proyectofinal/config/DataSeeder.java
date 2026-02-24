package com.cibertec.proyectofinal.config;

import com.cibertec.proyectofinal.model.Usuario;
import com.cibertec.proyectofinal.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        usuarioRepository.findByUsername("admin").ifPresentOrElse(
                u -> {
                    // admin123 en BCrypt
                    u.setPassword(passwordEncoder.encode("admin123"));
                    u.setRol("ADMIN");
                    u.setActivo(true);
                    usuarioRepository.save(u);
                },
                () -> {
                    Usuario admin = Usuario.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin123"))
                            .rol("ADMIN")
                            .activo(true)
                            .build();
                    usuarioRepository.save(admin);
                }
        );
    }
}
