-- Creación de tablas (ya creadas según tu comentario anterior)
-- INSERTS DE EJEMPLO 

-- Especialidades
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Deportiva', 'Se enfoca en la prevención, tratamiento y rehabilitación de lesiones deportivas.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Neurológica', 'Trata a pacientes con trastornos neurológicos, como accidentes cerebrovasculares, Parkinson, esclerosis múltiple, entre otros.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Pediátrica', 'Se centra en el tratamiento de problemas de movilidad y desarrollo motor en niños.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Geriátrica', 'Se especializa en el cuidado de adultos mayores, ayudándoles a mantener su movilidad e independencia.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Respiratoria', 'Ayuda a mejorar la función pulmonar y respiratoria en pacientes con enfermedades respiratorias crónicas como el asma o la enfermedad pulmonar obstructiva crónica (EPOC).');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Fisioterapia Ortopédica', 'Trata lesiones y disfunciones del sistema musculoesquelético, como fracturas, esguinces y problemas de columna vertebral.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Instructores de Fitness', 'Pueden tener certificaciones específicas en áreas como entrenamiento personal, aeróbicos, pilates, yoga, entre otros.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Profesores en Educación Física', 'Tienen formación académica y suelen tener un enfoque más amplio en la promoción de la actividad física y la salud.');
INSERT INTO `gesterindb`.`especialidades` (`nombre`, `descripcion`) VALUES ('Entrenadores Personales', 'Suelen estar certificados por organizaciones reconocidas y pueden especializarse en áreas como entrenamiento funcional, musculación, y entrenamiento de fuerza.');

-- Tipos de Doctores
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES ('Clínico', 'Pueden derivar a fisioterapeutas para una variedad de problemas musculoesqueléticos.');
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES ('Traumatólogos', 'Especialistas en lesiones musculoesqueléticas');
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES ('Ortopedistas', 'Especialistas en el sistema musculoesquelético');
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES ('Neurólogos', 'Especialistas en el sistema nervioso');
INSERT INTO `gesterindb`.`tipos_doctores` (`nombre`, `descripcion`) VALUES ('Reumatólogos', 'Especialistas en enfermedades reumáticas');

-- Tipos de Tratamientos
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES ('Masajes manual', '');
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES ('Termoterapia', 'efecto de calor');
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES ('Crio terapia', 'efecto frio');
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES ('Vibroterapia', '');
INSERT INTO `gesterindb`.`tipos_tratamientos` (`nombre`, `descripcion`) VALUES ('Magnetoterapia', '');

-- Lesiones
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Tendinitis', 'Lesiones Musculares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Distensiones musculares', 'Lesiones Musculares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Desgarros', 'Lesiones Musculares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Artritis', 'Lesiones Articulares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Lesiones en rodillas', 'Lesiones Articulares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Lesiones en hombros', 'Lesiones Articulares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Lesiones en codos', 'Lesiones Articulares');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Fracturas', 'Lesiones Debido a Accidentes');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Esguinces', 'Lesiones Debido a Accidentes');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Contusiones', 'Lesiones Debido a Accidentes');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Lesiones de ligamentos', 'Lesiones Ligamentosas');
INSERT INTO `gesterindb`.`lesiones` (`nombre`, `descripcion`) VALUES ('Espróntesis', 'Lesiones Ligamentosas');

-- Tipos de antecedentes
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Ninguno', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Alcoholismo', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Insomnio', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Alergia dérmica', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Alergia medicamento', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Presión Alta', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Presión Baja', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Sedentarismo', '');
INSERT INTO `gesterindb`.`tipos_antecedentes` (`nombre`, `descripcion`) VALUES ('Depepresión', '');

-- Patologías
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Artritis reumatoide', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Osteoporosis', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Enfermedad cardiovascular', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Hipertensión', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Insuficiencia cardíaca', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Arritmia cardíaca', '');
INSERT INTO `gesterindb`.`patologias` (`nombre`, `descripcion`) VALUES ('Diabetes tipo 1', '');

-- Doctores
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES ('doctor1@example.com', '1234567890', 'Carlos', 'Medina', 1);
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES ('doctor2@example.com', '0987654321', 'Laura', 'Fernandez', 2);
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES ('doctor3@example.com', '1122334455', 'Jorge', 'Gonzalez', 3);
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES ('doctor4@example.com', '5544332211', 'Sofia', 'Martinez', 4);
INSERT INTO `gesterindb`.`doctores` (`email`, `telefono`, `nombre`, `apellido`, `tipoDoctor_id`) VALUES ('doctor5@example.com', '6677889900', 'Mariana', 'Perez', 5);

-- Roles
INSERT INTO `gesterindb`.`roles` (`nombre`, `descripcion`) VALUES ('ADMINISTRADOR', 'Rol de administrador del sistema');
INSERT INTO `gesterindb`.`roles` (`nombre`, `descripcion`) VALUES ('PROFESIONAL', 'Rol de profesional del sistema');

-- Usuarios
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('Wolf103', '$2a$10$o7ajIM1XrQBOhHFXDAQa5OvcTU4pXjTt9Ur.dWagtqa4omJ4f745u', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('facuFC', '$2a$10$oaaAzR6/ml7O0qS9Ddc5q.2DIhsC137ImHSa./x8HdgWOcljZWRyu', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('silcapin', '$2a$10$T69YnAo7GV4g61bM0guN5OWy5.UpIzkSpCxj3zsDNSPMjlNMY0GtG', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('guillote', '$2a$10$tAvA7TjS8id6w.QL/xZaGO069XQwdayomDL/Y1bqXTWxh7aDEJ0Oi', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('jormi', '$2a$10$Z7ODBnnoUY.m46VuR2w9d.F69hRo9EUJqVqLlQbiu3v1P4S7JXMWW', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));

INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('geraProf', '$2a$10$8.wTIICCI2DWhF5s57CXpOzv9t22LnsumavrNa.9ggi983Ynx0O6e', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('vaneProf', '$2a$10$pdysXyRixuJygrlu16r.iOwi.TqPayUOD2YOz780Iwf3B5NLzziTO', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('SebasProf', '$2a$10$C4psKG0AoXHDnIrl1An4HOm99dhaMHmYKesszdHdltSg8pyVnhqoS', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('CarlosProf', '$2a$10$IvOKW5GqYM8IEODt2I2obuDVcAIFpMlFMluGWBTvJJ5ozZNy3LLDy', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES ('HernanProf', '$2a$10$0aPcAqZ4I33u4e11kS2uhX99oK', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));

-- People
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('wolf103@example.com', '3764112211', 'Cristian', 'Orzco');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('facuFC@example.com', '3764112222', 'Facundo', 'Caniza');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('silcapin@example.com', '3764112233', 'Silvana', 'Pintos');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('guillote@example.com', '3764112244', 'Guille', 'Vogel');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('jormi@example.com', '3764112255', 'Jorge', 'Daglio');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('geraProf@example.com', '3764112266', 'Gerardo', 'Viera');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('vaneProf@example.com', '3764112277', 'Vanesa', 'Pereyra');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('sebasProf@example.com', '3764112288', 'Sebastian', 'Villalba');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('carlosProf@example.com', '3764112299', 'Carlos', 'Rodriguez');
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES ('hernanProf@example.com', '3764112300', 'Hernan', 'Romano');

-- Administradores
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'wolf103@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'Wolf103'));
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'facuFC@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'facuFC'));
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'silcapin@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'silcapin'));
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'guillote@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'guillote'));
INSERT INTO `gesterindb`.`administradores` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'jormi@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'jormi'));

-- Profesionales
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'geraProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'geraProf'));
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'vaneProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'vaneProf'));
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'sebasProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'SebasProf'));
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'carlosProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'CarlosProf'));
INSERT INTO `gesterindb`.`profesionales` (`id`, `usuario_id`) VALUES ((SELECT id FROM `gesterindb`.`people` WHERE email = 'hernanProf@example.com'), (SELECT id FROM `gesterindb`.`usuarios` WHERE nombre = 'HernanProf'));

-- Clientes
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES ('32111000', 'cliente1@example.com', '3211100001', '3764225500', 'Juan', 'Perez', 1, 'Calle Falsa 123');
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES ('32111001', 'cliente2@example.com', '3211100102', '3764225501', 'Ana', 'Gomez', 1, 'Av. Siempre Viva 742');
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES ('32111002', 'cliente3@example.com', '3211100201', '3764225502', 'Luis', 'Lopez', 1, 'Calle 13 456');
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES ('32111003', 'cliente4@example.com', '3211100301', '3764225503', 'Maria', 'Martinez', 1, 'Av. Mitre 1000');
INSERT INTO `gesterindb`.`clientes` (`dni`, `email`, `obra_social`, `telefono`, `nombre`, `apellido`, `estado`, `direccion`) VALUES ('32111004', 'cliente5@example.com', '3211100401', '3764225504', 'Jose', 'Garcia', 1, 'Calle San Martin 987');

-- Características físicas
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES (1.75, 70.5, 120, 80, 1);
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES (1.60, 55.0, 110, 70, 2);
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES (1.80, 85.0, 130, 85, 3);
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES (1.65, 60.0, 115, 75, 4);
INSERT INTO `gesterindb`.`caracteristicas_fisicas` (`altura`, `peso`, `tensionArterialBI`, `tensionArterialBD`, `cliente_id`) VALUES (1.70, 75.0, 125, 80, 5);


-- Antecedentes personales
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 1', 1, 1);
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 2', 2, 2);
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 3', 3, 3);
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 3', 8, 3);
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 4', 8, 4);
INSERT INTO `gesterindb`.`antecedentes_personales` (`observacion`, `tipoAntecedente_id`, `cliente_id`) VALUES ('Observación 5', 8, 5);

-- Historiales clínicos
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES (12, 'Descripción Historial 1', 1);
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES (18, 'Descripción Historial 2', 2);
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES (24, 'Descripción Historial 3', 3);
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES (36, 'Descripción Historial 4', 4);
INSERT INTO `gesterindb`.`historiales_clinicos` (`duracion`, `descripcion`, `cliente_id`) VALUES (48, 'Descripción Historial 5', 5);

-- Historiales clínicos patologías
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES (2, 6);
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES (3, 3);
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES (3, 4);
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES (4, 1);
INSERT INTO `gesterindb`.`historiales_clinicos_patologias` (`historial_clinico_id`, `patologia_id`) VALUES (5, 7);

-- Historiales clínicos lesiones
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (1, 1);
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (1, 2);
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (2, 5);
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (3, 4);
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (4, 5);
INSERT INTO `gesterindb`.`historiales_clinicos_lesiones` (`hstorial_clinico_id`, `lesion_id`) VALUES (5, 1);

-- Derivaciones
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES ('2023-01-01', 'Diagnóstico 1', 'Observación 1', 1);
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES ('2023-02-01', 'Diagnóstico 2', 'Observación 2', 2);
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES ('2023-03-01', 'Diagnóstico 3', 'Observación 3', 3);
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES ('2023-04-01', 'Diagnóstico 4', 'Observación 4', 4);
INSERT INTO `gesterindb`.`derivaciones` (`fecha`, `diagnostico`, `observacion`, `doctor_id`) VALUES ('2023-05-01', 'Diagnóstico 5', 'Observación 5', 5);

-- Tratamientos
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES ('Descripción Tratamiento 1', 1, 1, 1, 1, 6);
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES ('Descripción Tratamiento 2', 1, 2, 2, 2, 7);
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES ('Descripción Tratamiento 3', 1, 3, 3, 3, 8);
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES ('Descripción Tratamiento 4', 1, 4, 4, 4, 9);
INSERT INTO `gesterindb`.`tratamientos` (`descripcion`, `estado`, `tipoTratamiento_id`, `derivacion_id`, `historialClinico_id`, `profesional_id`) VALUES ('Descripción Tratamiento 5', 1, 5, 5, 5, 10);

-- Sesiones
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-01 10:00:00', 'PENDIENTE', 1);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-03 10:00:00', 'PENDIENTE', 1);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-05 10:00:00', 'PENDIENTE', 1);

INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-02 11:00:00', 'PENDIENTE', 2);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-04 11:00:00', 'PENDIENTE', 2);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-06 11:00:00', 'PENDIENTE', 2);

INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-03 12:00:00', 'PENDIENTE', 3);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-05 12:00:00', 'PENDIENTE', 3);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-07 12:00:00', 'PENDIENTE', 3);

INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-04 13:00:00', 'PENDIENTE', 4);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-06 13:00:00', 'PENDIENTE', 4);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-08 13:00:00', 'PENDIENTE', 4);

INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-05 14:00:00', 'PENDIENTE', 5);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-07 14:00:00', 'PENDIENTE', 5);
INSERT INTO `gesterindb`.`sesiones` (`fecha_hora`, `estado`, `tratamiento_id`) VALUES ('2023-06-09 14:00:00', 'PENDIENTE', 5);

