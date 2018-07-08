package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping
    public String index(){
        System.out.println("Olá, Entrando no index.");
        return "Home"; //nao preisa do .jsp pq foi configurado lá no AppWebConfiguration pra nao precisar de .jsp
    }
}
