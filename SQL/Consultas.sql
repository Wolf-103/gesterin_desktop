-- Buscar usuarios por id tipo de rol
SELECT u.*
FROM gesterindb.usuarios u
JOIN gesterindb.roles r ON u.rol_id = r.id
WHERE r.id = 1;

-- Buscar usuarios por nombre
SELECT *
FROM gesterindb.usuarios u
WHERE u.nombre LIKE facu;

-- Buscar todos los usuarios
SELECT * FROM gesterindb.usuarios;

-- Buscar todos los roles
SELECT * FROM gesterindb.roles;