-- Generar los TIPO DE ANTECEDENTES
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