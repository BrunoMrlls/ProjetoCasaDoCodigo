package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator{
	
	public void valida(Produto produto, Errors errors) {
		

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	} //aki mostra as classes que esse validator suporta

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.requerid");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.requerid");
		
		Produto produto = (Produto)target;
		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.requerid");
		}
	}

}
