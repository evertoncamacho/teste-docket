package br.com.docket.teste.cartorio.repository;

import br.com.docket.teste.cartorio.model.Cartorio;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CartorioRepositoryCustomImpl implements CartorioRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Cartorio> buscaCartoriosCompletos() {
        List<Cartorio> cartorios = em.createQuery("SELECT DISTINCT a FROM Cartorio a JOIN FETCH a.certidoes ORDER BY a.nome", Cartorio.class)
                                        .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                                        .getResultList();

        return ((cartorios == null || cartorios.isEmpty()) ? null : cartorios);
    }

    @Override
    public Optional<Cartorio> buscaCartorioCompletoPorId(Long id) {
        List<Cartorio> cartorios = em.createQuery("SELECT DISTINCT a FROM Cartorio a JOIN FETCH a.certidoes WHERE a.id = :id", Cartorio.class)
                .setParameter("id", id)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();

        return (cartorios == null || cartorios.isEmpty() ? Optional.empty() : Optional.of(cartorios.get(0)));
    }
}
