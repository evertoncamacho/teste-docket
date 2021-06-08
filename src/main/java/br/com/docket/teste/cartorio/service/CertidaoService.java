package br.com.docket.teste.cartorio.service;

import br.com.docket.teste.cartorio.model.Certidao;
import br.com.docket.teste.cartorio.repository.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertidaoService {

    @Autowired
    private CertidaoRepository certidaoRepository;

    public List<Certidao> buscaCertidoesECartoriosRelacionados() {
        return certidaoRepository.buscaCertidoes();
    }

    public List<Certidao> buscaCertidoes() {
        return certidaoRepository.findAll();
    }

}
