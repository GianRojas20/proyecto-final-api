SET search_path TO app;

CREATE TABLE IF NOT EXISTS usuarios (
                                        id          BIGSERIAL PRIMARY KEY,
                                        username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    rol         VARCHAR(20)  NOT NULL DEFAULT 'USER',
    activo      BOOLEAN      NOT NULL DEFAULT TRUE,
    creado_en   TIMESTAMP    NOT NULL DEFAULT now()
    );

INSERT INTO usuarios (username, password, rol, activo)
VALUES
    ('admin', '$2a$10$7q1q1x0m2y2c2kPqzv1y6e1zqKxj6o4rCqvK2V3c9GmQxj2B3zH1m', 'ADMIN', TRUE),
    ('gian',  '$2a$10$u1pY0XoWj3J2h8r0QeKp1eT6eB8b9RrSx3zQwV7p2lK8oY3vZ1Y2K', 'USER',  TRUE)
    ON CONFLICT (username) DO NOTHING;

CREATE TABLE IF NOT EXISTS productos (
                                         id           BIGSERIAL PRIMARY KEY,
                                         nombre       VARCHAR(120) NOT NULL,
    descripcion  VARCHAR(250),
    precio       NUMERIC(10,2) NOT NULL CHECK (precio >= 0),
    stock        INT NOT NULL CHECK (stock >= 0),
    estado       BOOLEAN NOT NULL DEFAULT TRUE,
    creado_en    TIMESTAMP NOT NULL DEFAULT now()
    );

INSERT INTO productos (nombre, descripcion, precio, stock, estado)
VALUES
    ('Mouse Logitech G203', 'Mouse gamer básico', 89.90, 15, TRUE),
    ('Teclado Mecánico Redragon', 'Switch rojo, RGB', 169.90, 10, TRUE),
    ('SSD NVMe 1TB', 'Gen4, buen rendimiento', 299.90, 8, TRUE),
    ('Audífonos HyperX', 'Micrófono integrado', 199.90, 12, TRUE),
    ('Monitor 24 pulgadas', 'Full HD 75Hz', 449.90, 5, TRUE)
    ON CONFLICT DO NOTHING;