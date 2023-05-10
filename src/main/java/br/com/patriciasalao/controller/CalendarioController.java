package br.com.patriciasalao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


@Controller
public class CalendarioController {

    @GetMapping("/calendario")
    public String calendario(@RequestParam(value = "mes", required = false, defaultValue = "0") int mes,
                             @RequestParam(value = "ano", required = false, defaultValue = "0") int ano,
                             Model model) {
        LocalDate data;

        if (mes == 0 || ano == 0) {
            data = LocalDate.now();
        } else {
            data = LocalDate.of(ano, mes, 1);
        }

        String mesAno = data.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        model.addAttribute("mesAno", mesAno);

        int diasNoMes = data.lengthOfMonth();
        LocalDate primeiroDiaMes = data.withDayOfMonth(1);

        DayOfWeek primeiroDiaSemana = primeiroDiaMes.getDayOfWeek();
        int diaSemana = primeiroDiaSemana.getValue() % 7;

        Integer[][] dias = new Integer[6][7];

        int numDia = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if ((i == 0 && j < diaSemana) || numDia > diasNoMes) {
                    dias[i][j] = null;
                } else {
                    dias[i][j] = numDia++;
                }
            }
        }

        model.addAttribute("dias", dias);
        model.addAttribute("mes", data.getMonthValue());
        model.addAttribute("ano", data.getYear());

        System.out.println(mesAno);

        return "calendario";
    }


    @GetMapping("/mudar-mes")
    public String mudarMes(@RequestParam int mes, @RequestParam int ano, Model model) {
        LocalDate dataAtual = LocalDate.of(ano, mes, 1);

        if (dataAtual.getMonthValue() == 12) {
            model.addAttribute("proximoMes", 1);
            model.addAttribute("proximoAno", ano + 1);
        } else {
            model.addAttribute("proximoMes", mes + 1);
            model.addAttribute("proximoAno", ano);
        }

        if (dataAtual.getMonthValue() == 1) {
            model.addAttribute("mesAnterior", 12);
            model.addAttribute("anoAnterior", ano - 1);
        } else {
            model.addAttribute("mesAnterior", mes - 1);
            model.addAttribute("anoAnterior", ano);
        }

        model.addAttribute("mesAno", dataAtual.format(DateTimeFormatter.ofPattern("MMMM/yyyy")));
        model.addAttribute("dias", gerarDiasDoMes(mes, ano));

        return "calendario";
    }

    private List<List<Integer>> gerarDiasDoMes(int mes, int ano) {
        List<List<Integer>> dias = new ArrayList<>();
        LocalDate dataAtual = LocalDate.of(ano, mes, 1);
        int diasNoMes = dataAtual.lengthOfMonth();
        int diaDaSemana = dataAtual.getDayOfWeek().getValue() % 7;

        List<Integer> semana = new ArrayList<>();
        for (int i = 1; i < diaDaSemana; i++) {
            semana.add(null);
        }

        for (int i = 1; i <= diasNoMes; i++) {
            semana.add(i);
            if (semana.size() == 7) {
                dias.add(semana);
                semana = new ArrayList<>();
            }
        }

        if (!semana.isEmpty()) {
            while (semana.size() < 7) {
                semana.add(null);
            }
            dias.add(semana);
        }

        return dias;
    }

    @GetMapping("/calendario/avanca")
    public String avancarMes(@RequestParam("mes") int mes, @RequestParam("ano") int ano) {
        LocalDate dataAtual = LocalDate.of(ano, mes, 1);
        LocalDate dataPosterior = dataAtual.plusMonths(1);
        return "redirect:/calendario?mes=" + dataPosterior.getMonthValue() + "&ano=" + dataPosterior.getYear();
    }


    @GetMapping("/calendario/volta")
    public String voltarMes(@RequestParam("mes") int mes, @RequestParam("ano") int ano) {
        LocalDate dataAtual = LocalDate.of(ano, mes, 1);
        LocalDate dataAnterior = dataAtual.minusMonths(1);
        return "redirect:/calendario?mes=" + dataAnterior.getMonthValue() + "&ano=" + dataAnterior.getYear();
    }




    @GetMapping("/mes-atual")
    @ResponseBody
    public String getMesAtual() {
        LocalDate dataAtual = LocalDate.now();
        Month mesAtual = dataAtual.getMonth();
        return mesAtual.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }

    @GetMapping("/calendario/{ano}/{mes}/{dia}")
    public String exibirAgenda(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia, Model model) {
        // Lógica para exibir a agenda para o dia clicado
        return "agenda/agenda";
    }

}