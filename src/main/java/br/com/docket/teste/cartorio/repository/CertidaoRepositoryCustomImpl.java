package br.com.docket.teste.cartorio.repository;

import br.com.docket.teste.cartorio.model.Certidao;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CertidaoRepositoryCustomImpl implements CertidaoRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Certidao> buscaCertidoes() {
        List<Certidao> certidoes = em.createQuery("SELECT DISTINCT a FROM Certidao a INNER JOIN FETCH a.cartorios", Certidao.class)
                                        .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                                        .getResultList();

        return ((certidoes == null || certidoes.isEmpty()) ? null : certidoes);
    }
}
