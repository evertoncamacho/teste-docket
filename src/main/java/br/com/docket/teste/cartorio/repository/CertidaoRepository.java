package br.com.docket.teste.cartorio.repository;

import br.com.docket.teste.cartorio.model.Certidao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertidaoRepository extends JpaRepository<Certidao, Long>, CertidaoRepositoryCustom {

}
