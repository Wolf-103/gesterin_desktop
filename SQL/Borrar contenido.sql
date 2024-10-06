-- Borrar usuarios Profesores
-- DELETE FROM gesterindb.usuarios WHERE nombre LIKE '%Prof%';
DELETE FROM gesterindb.usuarios WHERE id IN (
    SELECT id FROM (
        SELECT id FROM gesterindb.usuarios WHERE nombre LIKE '%Prof%'
    ) AS subquery
);


-- Borrar usuarios Administradores
DELETE FROM gesterindb.usuarios WHERE nombre LIKE 'Wolf103';
DELETE FROM gesterindb.usuarios WHERE nombre LIKE 'facuFC';
DELETE FROM gesterindb.usuarios WHERE nombre LIKE 'silcapin';
DELETE FROM gesterindb.usuarios WHERE nombre LIKE 'guillote';
DELETE FROM gesterindb.usuarios WHERE nombre LIKE 'jormi';

-- Borrar roles 
DELETE FROM gesterindb.roles WHERE nombre LIKE 'ADMINISTRADOR';
DELETE FROM gesterindb.roles WHERE nombre LIKE 'PROFESIONAL';
