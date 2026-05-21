-- Remove a tabela se já existir
DROP TABLE IF EXISTS carro;

-- Cria a tabela com integridade
CREATE TABLE carro (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       modelo VARCHAR(100) NOT NULL,
                       ano INT NOT NULL,
                       CONSTRAINT chk_ano CHECK (ano >= 1886)
);

-- Zera os registros e insere dados iniciais
DELETE FROM carro;

INSERT INTO carro (modelo, ano) VALUES ('Uno', 2010);
INSERT INTO carro (modelo, ano) VALUES ('Celta', 2008);
INSERT INTO carro (modelo, ano) VALUES ('Fiesta', 2014);
