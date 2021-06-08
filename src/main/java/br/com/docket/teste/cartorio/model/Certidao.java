package br.com.docket.teste.cartorio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "certidao")
@Getter
@Setter
@NoArgsConstructor
public class Certidao {

    @Id
    @Column(name = "id_certidao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCertidao tipoCertidao;

    @ManyToMany(mappedBy = "certidoes")
    @JsonIgnoreProperties("certidoes")
    private Set<Cartorio> cartorios;

}
