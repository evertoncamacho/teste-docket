package br.com.docket.teste.cartorio.exception;

public class NumeroDeCertidoesInsuficienteException extends RuntimeException {

    public NumeroDeCertidoesInsuficienteException() {
        super("Por favor, selecione pelo menos uma certidão!");
    }
}

