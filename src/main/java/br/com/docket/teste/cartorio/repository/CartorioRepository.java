package br.com.docket.teste.cartorio.repository;

import br.com.docket.teste.cartorio.model.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends JpaRepository<Cartorio, Long>, CartorioRepositoryCustom {

}
