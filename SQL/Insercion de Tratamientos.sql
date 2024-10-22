-- Insertar Derivaciones
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES
('2023-01-01', 'Diagnóstico 1', 'Observación 1', 1),
('2023-02-01', 'Diagnóstico 2', 'Observación 2', 2),
('2023-03-01', 'Diagnóstico 3', 'Observación 3', 3),
('2023-04-01', 'Diagnóstico 4', 'Observación 4', 4),
('2023-05-01', 'Diagnóstico 5', 'Observación 5', 5);

-- Insertar Tratamientos
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES
('Descripción Tratamiento 1', 1, 1, 1, 1, 1),
('Descripción Tratamiento 2', 1, 2, 2, 2, 2),
('Descripción Tratamiento 3', 1, 3, 3, 3, 3),
('Descripción Tratamiento 4', 1, 4, 4, 4, 4),
('Descripción Tratamiento 5', 1, 5, 5, 5, 5);

-- Insertar Sesiones para Tratamiento 1
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES
('2023-06-01 10:00:00', 'PENDIENTE', 1),
('2023-06-03 10:00:00', 'PENDIENTE', 1),
('2023-06-05 10:00:00', 'PENDIENTE', 1);

-- Insertar Sesiones para Tratamiento 2
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES
('2023-06-02 11:00:00', 'PENDIENTE', 2),
('2023-06-04 11:00:00', 'PENDIENTE', 2),
('2023-06-06 11:00:00', 'PENDIENTE', 2);

-- Insertar Sesiones para Tratamiento 3
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES
('2023-06-03 12:00:00', 'PENDIENTE', 3),
('2023-06-05 12:00:00', 'PENDIENTE', 3),
('2023-06-07 12:00:00', 'PENDIENTE', 3);

-- Insertar Sesiones para Tratamiento 4
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES
('2023-06-04 13:00:00', 'PENDIENTE', 4),
('2023-06-06 13:00:00', 'PENDIENTE', 4),
('2023-06-08 13:00:00', 'PENDIENTE', 4);

-- Insertar Sesiones para Tratamiento 5
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES
('2023-06-05 14:00:00', 'PENDIENTE', 5),
('2023-06-07 14:00:00', 'PENDIENTE', 5),
('2023-06-09 14:00:00', 'PENDIENTE', 5);
