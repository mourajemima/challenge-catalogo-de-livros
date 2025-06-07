package dev.jemima.catalogodelivros;

import dev.jemima.catalogodelivros.principal.Principal;
import dev.jemima.catalogodelivros.service.ConfiguracaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class CatalogoDeLivrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoDeLivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConfiguracaoService.acessaVariaveis();
		Principal principal = new Principal();
		principal.menu();
	}
}
