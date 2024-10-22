-- Generar los TIPO DE ESPECIALIDAD DEL PROFESOR
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES
('Fisioterapia Deportiva', 'Se enfoca en la prevención, tratamiento y rehabilitación de lesiones deportivas.'),
('Fisioterapia Neurológica', 'Trata a pacientes con trastornos neurológicos, como accidentes cerebrovasculares, Parkinson, esclerosis múltiple, entre otros.'),
('Fisioterapia Pediátrica', 'Se centra en el tratamiento de problemas de movilidad y desarrollo motor en niños.'),
('Fisioterapia Geriátrica', 'Se especializa en el cuidado de adultos mayores, ayudándoles a mantener su movilidad e independencia.'),
('Fisioterapia Respiratoria', 'Ayuda a mejorar la función pulmonar y respiratoria en pacientes con enfermedades respiratorias crónicas como el asma o la enfermedad pulmonar obstructiva crónica (EPOC).'),
('Fisioterapia Ortopédica', 'Trata lesiones y disfunciones del sistema musculoesquelético, como fracturas, esguinces y problemas de columna vertebral.'),
('Instructores de Fitness', 'Pueden tener certificaciones específicas en áreas como entrenamiento personal, aeróbicos, pilates, yoga, entre otros.'),
('Profesores en Educación Física', 'Tienen formación académica y suelen tener un enfoque más amplio en la promoción de la actividad física y la salud.'),
('Entrenadores Personales', 'Suelen estar certificados por organizaciones reconocidas y pueden especializarse en áreas como entrenamiento funcional, musculación, y entrenamiento de fuerza.');

-- Generar los TIPO DE DOCTORES
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES
('Clínico', 'Pueden derivar a fisioterapeutas para una variedad de problemas musculoesqueléticos.'),
('Traumatólogos', 'Especialistas en lesiones musculoesqueléticas'),
('Ortopedistas', 'Especialistas en el sistema musculoesquelético'),
('Neurólogos', 'Especialistas en el sistema nervioso'),
('Reumatólogos', 'Especialistas en enfermedades reumáticas');

-- Generar los TIPO DE TRATAMIENTOS
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES
('Masajes manual', ''),
('Termoterapia', 'efecto de calor'),
('Crio terapia', 'efecto frio'),
('Vibroterapia', ''),
('Magnetoterapia', '');

-- Generar los TIPO DE LESIONES
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES
('Tendinitis', 'Lesiones Musculares'),
('Distensiones musculares', 'Lesiones Musculares'),
('Desgarros', 'Lesiones Musculares'),
('Artritis', 'Lesiones Articulares'),
('Lesiones en rodillas', 'Lesiones Articulares'),
('Lesiones en hombros', 'Lesiones Articulares'),
('Lesiones en codos', 'Lesiones Articulares'),
('Fracturas', 'Lesiones Debido a Accidentes'),
('Esguinces', 'Lesiones Debido a Accidentes'),
('Contusiones', 'Lesiones Debido a Accidentes'),
('Lesiones de ligamentos', 'Lesiones Ligamentosas'),
('Espróntesis', 'Lesiones Ligamentosas');

-- Generar los TIPO DE ANTECEDENTES
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES
('Ninguno', ''),
('Alcoholismo', ''),
('Insomnio', ''),
('Alergia dérmica', ''),
('Alergia medicamento', ''),
('Presión Alta', ''),
('Presión Baja', ''),
('Sedentarismo', ''),
('Depepresión', '');

-- Generar los TIPO DE PATOLOGIAS
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES
('Artritis reumatoide', ''),
('Osteoporosis', ''),
('Enfermedad cardiovascular', ''),
('Hipertensión', ''),
('Insuficiencia cardíaca', ''),
('Arritmia cardíaca', ''),
('Diabetes tipo 1', '');

-- Insertar Doctores
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES
('doctor1@example.com', '1234567890', 'Carlos', 'Medina', 1),  -- Clínico
('doctor2@example.com', '0987654321', 'Laura', 'Fernandez', 2), -- Traumatólogo
('doctor3@example.com', '1122334455', 'Jorge', 'Gonzalez', 3),  -- Ortopedista
('doctor4@example.com', '5544332211', 'Sofia', 'Martinez', 4),  -- Neurólogo
('doctor5@example.com', '6677889900', 'Mariana', 'Perez', 5);   -- Reumatólogo

-- Generar los ROLES
INSERT INTO `gesterindb`.`roles` (`nombre`, `descripcion`) VALUES
('ADMINISTRADOR', 'Rol de administrador del sistema'),
('PROFESIONAL', 'Rol de profesional del sistema');

-- Generar usuarios ADMINISTRADOR con estado ACTIVO POR DEFECTO
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES
('Wolf103', '$2a$10$o7ajIM1XrQBOhHFXDAQa5OvcTU4pXjTt9Ur.dWagtqa4omJ4f745u', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('facuFC', '$2a$10$oaaAzR6/ml7O0qS9Ddc5q.2DIhsC137ImHSa./x8HdgWOcljZWRyu', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('silcapin', '$2a$10$T69YnAo7GV4g61bM0guN5OWy5.UpIzkSpCxj3zsDNSPMjlNMY0GtG', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('guillote', '$2a$10$tAvA7TjS8id6w.QL/xZaGO069XQwdayomDL/Y1bqXTWxh7aDEJ0Oi', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('jormi', '$2a$10$Z7ODBnnoUY.m46VuR2w9d.F69hRo9EUJqVqLlQbiu3v1P4S7JXMWW', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));

-- Generar usuarios PROFESIONAL con estado ACTIVO POR DEFECTO
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES
('geraProf', '$2a$10$8.wTIICCI2DWhF5s57CXpOzv9t22LnsumavrNa.9ggi983Ynx0O6e', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('vaneProf', '$2a$10$pdysXyRixuJygrlu16r.iOwi.TqPayUOD2YOz780Iwf3B5NLzziTO', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('SebasProf', '$2a$10$C4psKG0AoXHDnIrl1An4HOm99dhaMHmYKesszdHdltSg8pyVnhqoS', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('CarlosProf', '$2a$10$IvOKW5GqYM8IEODt2I2obuDVcAIFpMlFMluGWBTvJJ5ozZNy3LLDy', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('HernanProf', '$2a$10$9/KMV/7QtHkV0GZFKT1IoOvdQ2.0aPcAqZ4I33u4e11kS2uhX99oK', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));

-- Insertar Personas
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES
('wolf103@example.com', '3764112211', 'Cristian', 'Orzco'),
('facuFC@example.com', '3764112222', 'Facundo', 'Caniza'),
('silcapin@example.com', '3764112233', 'Silvana', 'Pintos'),
('guillote@example.com', '3764112244', 'Guille', 'Vogel'),
('jormi@example.com', '3764112255', 'Jorge', 'Daglio'),
('geraProf@example.com', '3764112266', 'Gerardo', 'Viera'),
('vaneProf@example.com', '3764112277', 'Vanesa', 'Pereyra'),
('sebasProf@example.com', '3764112288', 'Sebastian', 'Villalba'),
('carlosProf@example.com', '3764112299', 'Carlos', 'Rodriguez'),
('hernanProf@example.com', '3764112300', 'Hernan', 'Romano');

-- Asignar Personas a Administradores
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES
((SELECT id FROM `gesterindb`.`people` WHERE email = 'wolf103@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'Wolf103')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'facuFC@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'facuFC')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'silcapin@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'silcapin')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'guillote@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'guillote')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'jormi@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'jormi'));

-- Asignar Personas a Profesionales
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES
((SELECT id FROM `gesterindb`.`people` WHERE email = 'geraProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'geraProf')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'vaneProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'vaneProf')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'sebasProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'SebasProf')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'carlosProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'CarlosProf')),
((SELECT id FROM `gesterindb`.`people` WHERE email = 'hernanProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'HernanProf'));

-- Insertar Clientes
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES
('32111000', 'cliente1@example.com', '3211100001', '3764225500', 'Juan', 'Perez', 1, 'Calle Falsa 123'),
('32111001', 'cliente2@example.com', '3211100102', '3764225501', 'Ana', 'Gomez', 1, 'Av. Siempre Viva 742'),
('32111002', 'cliente3@example.com', '3211100201', '3764225502', 'Luis', 'Lopez', 1, 'Calle 13 456'),
('32111003', 'cliente4@example.com', '3211100301', '3764225503', 'Maria', 'Martinez', 1, 'Av. Mitre 1000'),
('32111004', 'cliente5@example.com', '3211100401', '3764225504', 'Jose', 'Garcia', 1, 'Calle San Martin 987');

-- Insertar Características Físicas
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES
(1.75, 70.5, 120, 80, 1),
(1.60, 55.0, 110, 70, 2),
(1.80, 85.0, 130, 85, 3),
(1.65, 60.0, 115, 75, 4),
(1.70, 75.0, 125, 80, 5);

-- Insertar Antecedentes Personales
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES
-- tipo antecedentes NINGUNO
('Observación 1', 1, 1), 
-- tipo antecedentes ALCOHOLISMO
('Observación 2', 2, 2),
-- tipo antecedentes INSOMNIO Y SEDENTARISMO
('Observación 3', 3, 3),
('Observación 3', 8, 3),
-- tipo antecedentes SEDENTARISMO
('Observación 4', 8, 4),
-- tipo antecedentes SEDENTARISMO
('Observación 5', 8, 5);

-- Insertar Historiales Clínicos
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES
(12, 'Descripción Historial 1', 1),
(18, 'Descripción Historial 2', 2),
(24, 'Descripción Historial 3', 3),
(36, 'Descripción Historial 4', 4),
(48, 'Descripción Historial 5', 5);

-- Insertar Relación entre Historiales Clínicos y Patologías
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES
-- (1, 1),
-- PATOLOGIA Arritmia cardíaca
(2, 6),
-- PATOLOGIA Enfermedad cardiovascular - Hipertensión
(3, 3),
(3, 4),
-- PATOLOGIA Artritis reumatoide
(4, 1),
-- PATOLOGIA Diabetes tipo 1
(5, 7);

-- Insertar Relación entre Historiales Clínicos y Lesiones
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES
-- LESIONES - Tendinitis y Distensiones musculares
(1, 1),
(1, 2),
-- LESIONES - Lesiones en rodillas
(2, 5),
-- LESIONES - Artritis
(3, 4),
-- LESIONES - Lesiones en rodillas
(4, 5),
-- LESIONES - Tendinitis
(5, 1);

-- Insertar Derivaciones
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES
('2023-01-01', 'Diagnóstico 1', 'Observación 1', 1),
('2023-02-01', 'Diagnóstico 2', 'Observación 2', 2),
('2023-03-01', 'Diagnóstico 3', 'Observación 3', 3),
('2023-04-01', 'Diagnóstico 4', 'Observación 4', 4),
('2023-05-01', 'Diagnóstico 5', 'Observación 5', 5);

-- Insertar Tratamientos
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES
('Descripción Tratamiento 1', 1, 1, 1, 1, 6),
('Descripción Tratamiento 2', 1, 2, 2, 2, 7),
('Descripción Tratamiento 3', 1, 3, 3, 3, 8),
('Descripción Tratamiento 4', 1, 4, 4, 4, 9),
('Descripción Tratamiento 5', 1, 5, 5, 5, 10);

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

