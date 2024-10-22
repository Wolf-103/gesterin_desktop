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