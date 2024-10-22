-- Insertar Clientes
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES
('12345678', 'cliente1@example.com', 'OS1', '1234567890', 'Juan', 'Perez', 1, 'Calle Falsa 123'),
('87654321', 'cliente2@example.com', 'OS2', '0987654321', 'Ana', 'Gomez', 1, 'Av. Siempre Viva 742'),
('11223344', 'cliente3@example.com', 'OS3', '1122334455', 'Luis', 'Lopez', 1, 'Calle 13 456'),
('44332211', 'cliente4@example.com', 'OS4', '5544332211', 'Maria', 'Martinez', 1, 'Av. Mitre 1000'),
('55667788', 'cliente5@example.com', 'OS5', '6677889900', 'Jose', 'Garcia', 1, 'Calle San Martin 987');

-- Insertar Características Físicas
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES
(1.75, 70.5, 120, 80, 1),
(1.60, 55.0, 110, 70, 2),
(1.80, 85.0, 130, 85, 3),
(1.65, 60.0, 115, 75, 4),
(1.70, 75.0, 125, 80, 5);

-- Insertar Antecedentes Personales
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES
('Observación 1', 1, 1),
('Observación 2', 2, 2),
('Observación 3', 3, 3),
('Observación 4', 4, 4),
('Observación 5', 5, 5);

-- Insertar Historiales Clínicos
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES
(12, 'Descripción Historial 1', 1),
(18, 'Descripción Historial 2', 2),
(24, 'Descripción Historial 3', 3),
(36, 'Descripción Historial 4', 4),
(48, 'Descripción Historial 5', 5);

-- Insertar Relación entre Historiales Clínicos y Patologías
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insertar Relación entre Historiales Clínicos y Lesiones
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
