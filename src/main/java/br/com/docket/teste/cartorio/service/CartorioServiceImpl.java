package br.com.docket.teste.cartorio.service;

import br.com.docket.teste.cartorio.exception.CartorioNaoEncontradoException;
import br.com.docket.teste.cartorio.exception.NumeroDeCertidoesInsuficienteException;
import br.com.docket.teste.cartorio.model.Cartorio;
import br.com.docket.teste.cartorio.repository.CartorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartorioServiceImpl implements CartorioService {

    @Autowired
    private CartorioRepository cartorioRepository;

    @Override
    public Cartorio salvaOuAtualiza(Cartorio cartorio) {

        if (cartorio.getCertidoes() == null || cartorio.getCertidoes().size() < 1) {
            throw new NumeroDeCertidoesInsuficienteException();
        }

        return cartorioRepository.save(cartorio);
    }

    @Override
    public List<Cartorio> buscaTodos() {
        return cartorioRepository.buscaCartoriosCompletos();
    }

    @Override
    public Optional<Cartorio> procuraPorId(Long id) {
        Optional<Cartorio> cartorio = cartorioRepository.buscaCartorioCompletoPorId(id);

        if (!cartorio.isPresent()) {
            throw new CartorioNaoEncontradoException();
        }

        return cartorio;
    }

    @Override
    public void remove(Cartorio cartorio) {
        cartorioRepository.delete(cartorio);
    }

    public Optional<Cartorio> buscaApenasCartorioPorId(Long id) {
        Optional<Cartorio> cartorio = cartorioRepository.findById(id);

        if (!cartorio.isPresent()) {
            throw new CartorioNaoEncontradoException();
        }

        return cartorio;
    }

    public Cartorio atualizaCartorioApi(Cartorio cartorioRequisicao, Long id) {
        Cartorio cartorioAtualizado = procuraPorId(id).get();

        cartorioAtualizado.setNome(cartorioRequisicao.getNome());
        cartorioAtualizado.setEndereco(cartorioRequisicao.getEndereco());
        cartorioAtualizado.setCertidoes(cartorioRequisicao.getCertidoes());

        return salvaOuAtualiza(cartorioAtualizado);
    }
}
