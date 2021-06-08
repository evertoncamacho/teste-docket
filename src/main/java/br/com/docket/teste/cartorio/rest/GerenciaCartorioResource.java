package br.com.docket.teste.cartorio.rest;

import br.com.docket.teste.cartorio.model.Cartorio;
import br.com.docket.teste.cartorio.model.Certidao;
import br.com.docket.teste.cartorio.service.CartorioServiceImpl;
import br.com.docket.teste.cartorio.service.CertidaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GerenciaCartorioResource {

    @Autowired
    private CartorioServiceImpl cartorioService;

    @Autowired
    private CertidaoService certidaoService;

    @GetMapping("/listaCertidoesRelacionadasComCartorios")
    public ResponseEntity<List<Certidao>> relacionaCertidoesComCartorios() {
        return ResponseEntity.ok(certidaoService.buscaCertidoesECartoriosRelacionados());
    }

    @GetMapping("/cartorios")
    public ResponseEntity<List<Cartorio>> exibeCartorios() {
        return ResponseEntity.ok(cartorioService.buscaTodos());
    }

    @GetMapping("/cartorios/{id}")
    public ResponseEntity<Cartorio> buscaCartorio(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cartorioService.procuraPorId(id).get());
    }

    @PostMapping("/cartorios/salvar")
    public ResponseEntity<Cartorio> salvaCartorio(@Valid @RequestBody Cartorio cartorio) {
        return new ResponseEntity<>(cartorioService.salvaOuAtualiza(cartorio), HttpStatus.CREATED);
    }

    @PutMapping("/cartorios/editar/{id}")
    public ResponseEntity<Cartorio> editaCartorio(@Valid @RequestBody Cartorio cartorioRequisicao, @PathVariable("id") Long id) {
        return ResponseEntity.ok(cartorioService.atualizaCartorioApi(cartorioRequisicao, id));
    }

    @DeleteMapping("/cartorios/remove/{id}")
    public ResponseEntity<Void> removeCartorio(@PathVariable("id") Long id) {

        cartorioService.remove(cartorioService.buscaApenasCartorioPorId(id).get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
