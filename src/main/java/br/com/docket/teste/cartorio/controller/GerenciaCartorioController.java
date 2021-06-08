package br.com.docket.teste.cartorio.controller;

import br.com.docket.teste.cartorio.exception.CartorioNaoEncontradoException;
import br.com.docket.teste.cartorio.exception.NumeroDeCertidoesInsuficienteException;
import br.com.docket.teste.cartorio.model.Cartorio;
import br.com.docket.teste.cartorio.service.CartorioServiceImpl;
import br.com.docket.teste.cartorio.service.CertidaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class GerenciaCartorioController {

    @Autowired
    private CartorioServiceImpl cartorioService;

    @Autowired
    private CertidaoService certidaoService;

    @GetMapping("/cartorios")
    public String exibeCartorios(Model model) {
        model.addAttribute("listaCartorios", cartorioService.buscaTodos());
        return "gerencia-cartorios";
    }

    @GetMapping("/cadastra-cartorio")
    public String exibeCadastroCartorio(@ModelAttribute("cartorio") Cartorio cartorio, Model model) {
        model.addAttribute("listaCertidoes", certidaoService.buscaCertidoes());
        return "form-cartorio";
    }

    @PostMapping("/salva-cartorio")
    public String salvaOuAtualizaCartorio(@Valid @ModelAttribute("cartorio") Cartorio cartorio, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("listaCertidoes", certidaoService.buscaCertidoes()); // Mantém a lista de certidões na view em caso de possíveis erros
        String msgErro;

        if (bindingResult.hasErrors()) {
            return "form-cartorio";
        }

        try {
            cartorioService.salvaOuAtualiza(cartorio);
            redirectAttributes.addFlashAttribute("msg", "Operação realizada com sucesso!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/cartorios";
        } catch (NumeroDeCertidoesInsuficienteException erro) {
            msgErro = erro.getMessage();
        } catch (Exception erro) {
            msgErro = "Erro interno. Por favor, tente novamente!";
        }

        model.addAttribute("msg", msgErro);
        model.addAttribute("alertClass", "alert-danger");
        return "form-cartorio";
    }

    @GetMapping("/cartorios/{id}")
    public String editaCartorio(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String msgErro;

        try {
            Optional<Cartorio> cartorio = cartorioService.procuraPorId(id);
            model.addAttribute("listaCertidoes", certidaoService.buscaCertidoes());
            model.addAttribute("modoEdicao", true);
            model.addAttribute("cartorio", cartorio.get());
            return "form-cartorio";
        } catch (CartorioNaoEncontradoException erro) {
            msgErro = erro.getMessage();
        } catch (Exception erro) {
            msgErro = "Erro interno. Por favor, tente novamente!";
        }

        redirectAttributes.addFlashAttribute("msg", msgErro);
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/cartorios";
    }

    @PostMapping("/cartorios/excluir/{id}")
    public String removeCartorio(@PathVariable("id") long id) {
        Optional<Cartorio> cartorio = cartorioService.buscaApenasCartorioPorId(id);
        cartorioService.remove(cartorio.get());
        return "redirect:/cartorios";
    }
}
