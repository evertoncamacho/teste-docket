package br.com.docket.teste.cartorio.service;

import br.com.docket.teste.cartorio.model.Cartorio;

import java.util.List;
import java.util.Optional;

public interface CartorioService {

    Cartorio salvaOuAtualiza(Cartorio cartorio);

    List<Cartorio> buscaTodos();

    Optional<Cartorio> procuraPorId(Long id);

    void remove(Cartorio cartorio);
}
