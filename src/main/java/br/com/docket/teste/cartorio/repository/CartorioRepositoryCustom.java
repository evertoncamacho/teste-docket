package br.com.docket.teste.cartorio.repository;

import br.com.docket.teste.cartorio.model.Cartorio;

import java.util.List;
import java.util.Optional;

public interface CartorioRepositoryCustom {

    List<Cartorio> buscaCartoriosCompletos();

    Optional<Cartorio> buscaCartorioCompletoPorId(Long id);
}
