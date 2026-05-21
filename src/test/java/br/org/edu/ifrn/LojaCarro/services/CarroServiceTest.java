package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarroServiceTest {

    @Autowired
    private CarroService carroService;

    // Busca de veículo por ID (já existe no data.sql)
    @Test
    void deveBuscarCarroPorId() {
        var carro = carroService.findById(1L).orElseThrow();
        assertEquals("Uno", carro.getModelo());
        assertEquals(2010, carro.getAno());
    }

    // Busca de todos os veículos (já existem 3 no data.sql)
    @Test
    void deveBuscarTodosCarros() {
        var carros = carroService.findAll();
        assertTrue(carros.size() >= 3); // já existem 3 do data.sql, mas pode haver mais
    }

    // Cadastro de veículo (insere novo registro)
    @Test
    void deveCadastrarCarro() {
        Carro novo = new Carro();
        novo.setModelo("Honda");
        novo.setAno(2021);
        novo.setPreco(85000.0); // ✅ preço como Double

        Carro salvo = carroService.save(novo);

        assertNotNull(salvo.getId());
        assertEquals("Honda", salvo.getModelo());
    }

    // Atualização de veículo
    @Test
    void deveAtualizarCarro() {
        var carro = carroService.findById(2L).orElseThrow(); // Celta
        carro.setAno(2020);
        Carro atualizado = carroService.update(carro);

        assertEquals(2020, atualizado.getAno());
    }

    // Remoção de veículo
    @Test
    void deveRemoverCarro() {
        Carro carro = new Carro();
        carro.setModelo("Ford");
        carro.setAno(2015);
        carro.setPreco(45000.0); // ✅ preço como Double

        carro = carroService.save(carro);

        carroService.deleteById(carro.getId());

        assertFalse(carroService.findById(carro.getId()).isPresent());
    }
}
