package br.com.patriciasalao.controller;

import br.com.patriciasalao.model.Funcionario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
public class AgendaController {
    @GetMapping("/agenda")
    public String exibirAgenda(@RequestParam("mesAno") String mesAno, @RequestParam("dia") String dia, Model model) {
        // Lógica para exibir a agenda para o dia clicado

        String[] partes = mesAno.split(" ");
        String mes = partes[0];
        String ano = partes[1];

        LocalDate data = LocalDate.parse(dia + " " + mes + " " + ano, DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("pt", "BR")));

        String diaSemana = data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));


        // Converte a data para o tipo LocalDate

        model.addAttribute("mesAno", mesAno);
        model.addAttribute("mes", mes);
        model.addAttribute("ano", ano);
        model.addAttribute("dia", dia);
        model.addAttribute("diaSemana", diaSemana);
        return "agenda/agenda";
    }

    @PostMapping("/agenda/adicionarFuncionaria")
    public String adicionarFuncionaria(@RequestParam("nomeFuncionaria") String nomeFuncionaria) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nomeFuncionaria);
        funcionarioRepository.save(funcionario);
        return "redirect:/agenda"; // redireciona para a página da agenda
    }
}
