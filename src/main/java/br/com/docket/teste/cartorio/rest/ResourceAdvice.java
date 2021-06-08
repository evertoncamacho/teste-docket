package br.com.docket.teste.cartorio.rest;

import br.com.docket.teste.cartorio.exception.CartorioNaoEncontradoException;
import br.com.docket.teste.cartorio.exception.NumeroDeCertidoesInsuficienteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartorioNaoEncontradoException.class)
    public void NaoEncontrado() {

    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NumeroDeCertidoesInsuficienteException.class)
    public void numeroDeCertidoesInsuficiente() {
    }

}
