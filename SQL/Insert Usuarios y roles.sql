-- Generar los ROLES
INSERT INTO `gesterindb`.`roles` (`nombre`, `descripcion`) VALUES
('ADMINISTRADOR', 'Rol de administrador del sistema'),
('PROFESIONAL', 'Rol de profesional del sistema');

-- Generar usuarios ADMINISTRADOR con estado ACTIVO POR DEFECTO
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES
('Wolf103', 'Donna103', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('facuFC', 'Fcaniza', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('silcapin', 'Kilian63', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('guillote', 'muFasa33', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR')),
('jormi', 'daglioM44', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'ADMINISTRADOR'));

-- Generar usuarios PROFESIONAL con estado ACTIVO POR DEFECTO
INSERT INTO `gesterindb`.`usuarios` (`nombre`, `contrasena`, `estado`, `rol_id`) VALUES
('geraProf', 'Gerardo901', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('vaneProf', 'Vanesa783', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('SebasProf', 'Sebastian362', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('CarlosProf', 'Carlos887', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL')),
('HernanProf', 'Hernan656', 1, (SELECT id FROM `gesterindb`.`roles` WHERE nombre = 'PROFESIONAL'));

-- Insertar Personas
INSERT INTO `gesterindb`.`people` (`email`, `telefono`, `nombre`, `apellido`) VALUES
('wolf103@example.com', '3764112211', 'Wolf', 'Admin'),
('facuFC@example.com', '3764112222', 'Facu', 'Caniza'),
('silcapin@example.com', '3764112233', 'Silca', 'Pin'),
('guillote@example.com', '3764112244', 'Guillo', 'Te'),
('jormi@example.com', '3764112255', 'Jor', 'Mi'),
('geraProf@example.com', '3764112266', 'Gera', 'Prof'),
('vaneProf@example.com', '3764112277', 'Vane', 'Prof'),
('sebasProf@example.com', '3764112288', 'Sebas', 'Prof'),
('carlosProf@example.com', '3764112299', 'Carlos', 'Prof'),
('hernanProf@example.com', '3764112300', 'Hernan', 'Prof');

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


