package br.com.patriciasalao.controller;

import br.com.patriciasalao.bo.FuncionarioBO;
import br.com.patriciasalao.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioBO bo;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        model.addAttribute("funcionario", new Funcionario());
        return "/funcionario/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String adiciona(@ModelAttribute Funcionario funcionario){
        bo.insere(funcionario);
        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model){
        model.addAttribute("funcionarios", bo.listaTodos());
        return new ModelAndView("funcionario/funcionarios", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public String edita(@PathVariable("id") Long id, ModelMap model) {
        try {
            model.addAttribute("funcionario", bo.pesquisaPeloId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/funcionario/formulario";
    }

    @RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id) {
        try {
            Funcionario funcionario = bo.pesquisaPeloId(id);
            bo.inativa(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id) {
        try {
            Funcionario funcionario = bo.pesquisaPeloId(id);
            bo.ativa(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/funcionarios";
    }

}