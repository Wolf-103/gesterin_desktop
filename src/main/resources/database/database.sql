CREATE TABLE
    `gesterindb`.`pacientes` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `dni` VARCHAR(13) NOT NULL,
        `email` VARCHAR(255) NULL,
        `obra_social` VARCHAR(10) NULL,
        `telefono` VARCHAR(12) NOT NULL,
        `nombre` VARCHAR(45) NOT NULL,
        `apellido` VARCHAR(45) NOT NULL,
        `estado` TINYINT (1) NOT NULL,
        `direccion` VARCHAR(1200) NULL,
        `caracteristicasFisicas_id` BIGINT UNIQUE NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
        UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
        UNIQUE INDEX `obra_social_UNIQUE` (`obra_social` ASC) VISIBLE,
        UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
        FOREIGN KEY (`caracteristicasFisicas_id`) REFERENCES `gesterindb`.`caracteristicas_fisicas` (`id`)
    );

CREATE TABLE
    `gesterindb`.`caracteristicas_fisicas` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `altura` FLOAT NOT NULL,
        `peso` FLOAT NOT NULL,
        `tensionArterialBI` FLOAT NULL,
        `tensionArterialBD` FLOAT NULL,
        `cliente_id` BIGINT UNIQUE NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`cliente_id`) REFERENCES `gesterindb`.`clientes` (`id`)
    );

CREATE TABLE
    `gesterindb`.`antecedentes_personales` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `observacion` VARCHAR(255) NOT NULL,
        `tipoAntecedente_id` BIGINT NOT NULL,
        `cliente_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`tipoAntecedente_id`) REFERENCES `gesterindb`.`tipos_antecedentes` (`id`),
        FOREIGN KEY (`cliente_id`) REFERENCES `gesterindb`.`clientes` (`id`)
    );

CREATE TABLE
    `gesterindb`.`tipos_antecedentes` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(120) NOT NULL,
        `descripcion` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_tipo_antecedente_UNIQUE` (`nombre` ASC) VISIBLE
    );

CREATE TABLE
    `gesterindb`.`historiales_clinicos` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `duracion` FLOAT NULL,
        `descripcion` VARCHAR(1200) NULL,
        `cliente_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`cliente_id`) REFERENCES `gesterindb`.`clientes` (`id`)
    );

CREATE TABLE
    `gesterindb`.`patologias` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(120) NOT NULL,
        `descripcion` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_patologia_UNIQUE` (`nombre` ASC) VISIBLE
    );

CREATE TABLE
    `gesterindb`.`lesiones` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(120) NOT NULL,
        `descripcion` VARCHAR(255) NULL,
        `extremidad` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_lesion_UNIQUE` (`nombre` ASC) VISIBLE
    );

-- Relación de muchos a mcuhos de historial clinico con patologí
CREATE TABLE
    `gesterindb`.`historiales_clinicos_patologias` (
        `historial_clinico_id` BIGINT NOT NULL,
        `patologia_id` BIGINT NOT NULL,
        PRIMARY KEY (`historial_clinico_id`, `patologia_id`),
        FOREIGN KEY (`historial_clinico_id`) REFERENCES `gesterindb`.`historiales_clinicos` (`id`),
        FOREIGN KEY (`patologia_id`) REFERENCES `gesterindb`.`patologias` (`id`)
    );

CREATE TABLE
    `gesterindb`.`historiales_clinicos_lesiones` (
        `hstorial_clinico_id` BIGINT NOT NULL,
        `lesion_id` BIGINT NOT NULL,
        PRIMARY KEY (`historial_clinico_id`, `lesion_id`),
        FOREIGN KEY (`historial_clinico_id`) REFERENCES `gesterindb`.`historiales_clinicos` (`id`),
        FOREIGN KEY (`lesion_id`) REFERENCES `gesterindb`.`lesiones` (`id`)
    );

CREATE TABLE
    `gesterindb`.`tratamientos` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `descripcion` VARCHAR(1200) NULL,
        `estado` TINYINT (1) NOT NOT,
        `tipoTratamiento_id` BIGINT NOT NULL,
        `derivacion_id` BIGINT NOT NULL UNIQUE,
        `historialClinico_id` BIGINT NOT NULL,
        `profesional_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`tipoTratamiento_id`) REFERENCES `gesterindb`.`tipos_tratamientos` (`id`),
        FOREIGN KEY (`derivacion_id`) REFERENCES `gesterindb`.`derivaciones` (`id`),
        FOREIGN KEY (`historial_clinico_id`) REFERENCES `gesterindb`.`historiales_clinicos` (`id`),
        FOREIGN KEY (`profesional_id`) REFERENCES `gesterindb`.`profesionales` (`id`)
    );

CREATE TABLE
    `gesterindb`.`tipos_tratamientos` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(45) NOT NULL,
        `descripcion` VARCHAR(1200) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_tipo_tratamiento_UNIQUE` (`nombre` ASC) VISIBLE
    );

CREATE TABLE
    `gesterindb`.`sesiones` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `fecha_hora` DATETIME NOT NULL,
        `estado` ENUM (
            'PENDIENTE',
            'EN_CURSO',
            'SUSPENDIDO',
            'FINALIZADO'
        ) NOT NULL,
        `tratamiento_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`tratamiento_id`) REFERENCES `gesterindb`.`tratamientos` (`id`)
    );

CREATE TABLE
    `gesterindb`.`profesionales` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `email` VARCHAR(255) NULL,
        `telefono` VARCHAR(12) NOT NULL,
        `nombre` VARCHAR(45) NOT NULL,
        `apellido` VARCHAR(45) NOT NULL,
        `usuario_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
        UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
        UNIQUE INDEX `obra_social_UNIQUE` (`obra_social` ASC) VISIBLE,
        UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
        FOREIGN KEY (`usuario_id`) REFERENCES `gesterindb`.`usuerios` (`id`)
    );

CREATE TABLE
    `gesterindb`.`especialidades` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(45) NOT NULL,
        `descripcion` VARCHAR(1200) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_especialidad_UNIQUE` (`nombre` ASC) VISIBLE
    );

-- Relacion de muchos a muchos de Profesional y especialidades
CREATE TABLE
    `gesterindb`.`profesional_especialidades` (
        `profesional_id` BIGINT NOT NULL,
        `especialidad_id` BIGINT NOT NULL,
        PRIMARY KEY (`profesional_id`, `especialidad_id`),
        FOREIGN KEY (`profesional_id`) REFERENCES `gesterindb`.`profesionales` (`id`),
        FOREIGN KEY (`especialidad_id`) REFERENCES `gesterindb`.`especialidades` (`id`)
    );

CREATE TABLE
    `gesterindb`.`usuarios` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(45) NOT NULL,
        `contrasena` VARCHAR(255) NOT NULL,
        `estado` TINYINT (1) NOT NULL,
        `rol_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`rol_id`) REFERENCES `gesterindb`.`roles` (`id`),
        UNIQUE INDEX `nombre_usuario_UNIQUE` (`nombre` ASC) VISIBLE
    );

CREATE TABLE
    `gesterindb`.`roles` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(45) NOT NULL,
        `descripcion` VARCHAR(1200) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_rol_UNIQUE` (`nombre_rol` ASC) VISIBLE
    );

CREATE TABLE
    `gesterindb`.`derivaciones` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `fecha` DATE NOT NULL,
        `diagnostico` VARCHAR(2000) NOT NULL,
        `observacion` VARCHAR(2000) NULL,
        `doctor_id` BIGINT NOT NULL,
        PRIMARY KEY (`id`),
        FOREIGN KEY (`doctor_id`) REFERENCES `gesterindb`.`doctores` (`id`)
    );

CREATE TABLE
    `gesterindb`.`doctores` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `email` VARCHAR(255) NULL,
        `telefono` VARCHAR(12) NOT NULL,
        `nombre` VARCHAR(45) NOT NULL,
        `apellido` VARCHAR(45) NOT NULL,
        `tipoDoctor_id` BIGINT NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
        UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
        UNIQUE INDEX `obra_social_UNIQUE` (`obra_social` ASC) VISIBLE,
        UNIQUE INDEX `telefono_UNIQUE` (`telefono` ASC) VISIBLE,
        FOREIGN KEY (`tipoDoctor_id`) REFERENCES `gesterindb`.`tipos_doctores` (`id`)
    );

CREATE TABLE
    `gesterindb`.`tipos_doctores` (
        `id` BIGINT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(45) NOT NULL,
        `descripcion` VARCHAR(1200) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `nombre_tipo_doctor_UNIQUE` (`nombre` ASC) VISIBLE
    );