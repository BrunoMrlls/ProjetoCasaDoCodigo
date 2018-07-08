package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private RestTemplate rest;
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return() -> { //libera todas as outras requisições e deixo só essa esperando.
			System.out.println(carrinho.getTotal());
			try {
				String uri =  "http://book-payment.herokuapp.com/payment";
				String response = rest.postForObject(uri,  new DadosPagamento(carrinho.getTotal()), String.class);
				model.addFlashAttribute("msg", response);
				return new ModelAndView("redirect:/produtos");
			}catch(HttpClientErrorException e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				model.addFlashAttribute("msg", "Valor maior que o permitido.");
				return new ModelAndView("redirect:/produtos");
			}
		};
		
	}
	
}
