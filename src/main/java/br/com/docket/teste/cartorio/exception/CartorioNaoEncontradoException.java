package br.com.docket.teste.cartorio.exception;

public class CartorioNaoEncontradoException extends RuntimeException {

    public CartorioNaoEncontradoException() {
        super("Este cartório não foi encontrado!");
    }
}

