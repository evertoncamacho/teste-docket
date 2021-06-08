BEGIN;
	INSERT INTO certidao (tipo_certidao) VALUES ('NASCIMENTO'), ('CASAMENTO'), ('OBITO');
	
	INSERT INTO cartorio (endereco, nome) VALUES ('Rua Um, 543', 'Cartório de Notas'), ('Rua Dois, 930', 'Cartório de Registro Civil'); 
	
	INSERT INTO cartorio_certidao (id_cartorio, id_certidao) VALUES (1, 1), (1,2), (2, 1), (2, 2), (2,3);
	
COMMIT;
				