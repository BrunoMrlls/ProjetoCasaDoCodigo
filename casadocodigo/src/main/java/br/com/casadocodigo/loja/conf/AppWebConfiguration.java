package br.com.casadocodigo.loja.conf;

import br.com.casadocodigo.loja.DAO.ProdutoDAO;
import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

import org.hibernate.transform.ResultTransformer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class, 
		FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration {

    @Bean //deixar classe gerenciada pelo bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); //onde ficam as páginas
        resolver.setSuffix(".jsp"); //pra nao precisar ficar digitando .jsp em todas as paginas.
        	
        resolver.setExposedContextBeanNames("carrinhoCompras");
        
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource() { //configuração do arq. messages.properties
    	ReloadableResourceBundleMessageSource source 
    		= new ReloadableResourceBundleMessageSource();
    	source.setBasename("/WEB-INF/messages");
    	source.setDefaultEncoding("UTF-8");
    	source.setCacheSeconds(1);
    	
    	return source;
    }
    
    @Bean//registrador para ajustar o padrao de data dd/MM/yyyy
    public FormattingConversionService mvcConversionService() {
    	DefaultFormattingConversionService conversionService 
    		= new DefaultFormattingConversionService();
    	DateFormatterRegistrar registrar = new DateFormatterRegistrar();
    	
    	registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
    	
    	registrar.registerFormatters(conversionService);
    	
    	return conversionService;
    }
    
    @Bean //para selecionar arq na view
    public MultipartResolver multipartResolver() {
    	return new StandardServletMultipartResolver();
    }
    
    @Bean
    public RestTemplate restTemplate() {
		return new RestTemplate();
    	
    }

}
