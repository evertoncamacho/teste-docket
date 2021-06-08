package br.com.docket.teste.cartorio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "cartorio")
@Getter
@Setter
@NoArgsConstructor
public class Cartorio {

    @Id
    @Column(name = "id_cartorio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Por favor, insira um nome!")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Por favor, insira um endereço!")
    @Column(nullable = false)
    private String endereco;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cartorio_certidao",
            joinColumns = @JoinColumn(name = "id_cartorio"),
            inverseJoinColumns = @JoinColumn(name = "id_certidao"))
    @JsonIgnoreProperties("cartorios")
    private Set<Certidao> certidoes = new HashSet<>();

    public List<Long> listaIdsCertidoes() {
        ArrayList<Long> listaIdsCertidoes = new ArrayList();

        if (this.certidoes != null) {
            for (Certidao certidao : this.certidoes) {
                listaIdsCertidoes.add(certidao.getId());
            }
        }

        return listaIdsCertidoes;
    }
}
