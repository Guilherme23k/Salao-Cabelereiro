package br.com.patriciasalao.controller;

import br.com.patriciasalao.bo.ClienteBO;
import br.com.patriciasalao.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteBO bo;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model){
        model.addAttribute("cliente", new Cliente());
        return new ModelAndView("/cliente/formulario", model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salva(@ModelAttribute Cliente cliente) {
        bo.insere(cliente);
        return "/cliente/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model){
        model.addAttribute("clientes", bo.listaTodos());
        return new ModelAndView("/cliente/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable("id") Long id, ModelMap model){
        try {
            model.addAttribute("cliente", bo.pesquisaPeloId(id));
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("/cliente/formulario", model);
    }

    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id){
        try{
            Cliente cliente = bo.pesquisaPeloId(id);
            bo.inativa(cliente);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/clientes";
    }

    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
        System.out.println(id);
        try {
            Cliente cliente = bo.pesquisaPeloId(id);
            bo.ativa(cliente);
            attr.addFlashAttribute("feedback", "Cliente foi ativado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/clientes";
    }

}
