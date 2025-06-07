package dev.jemima.catalogodelivros.principal;

import dev.jemima.catalogodelivros.model.DadosLivro;
import dev.jemima.catalogodelivros.model.Livro;
import dev.jemima.catalogodelivros.model.RespostaGutendex;
import dev.jemima.catalogodelivros.service.ConsumoApi;
import dev.jemima.catalogodelivros.service.ConverteDados;
import dev.jemima.catalogodelivros.service.ConverteLivro;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi api = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public void menu() throws IOException, InterruptedException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores resgistrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscaLivro();
                    break;
                case 2:
                    listaLivrosResgritrados();
                    break;
                case 3:
                    listaAutoresResgritrados();
                    break;
                case 4:
                    autoresVivosEmAno();
                    break;
                case 5:
                    livrosEmIdioma();
                    break;
                case 0:
                    System.out.println("Finalizando aplicação");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void buscaLivro() throws IOException, InterruptedException {
        System.out.println("Busque um livro pelo título: ");
        var livroBusca = scanner.nextLine();
        var json = api.consomeApi(ENDERECO + livroBusca.replace(" ", "%20"));
        RespostaGutendex resposta = conversor.obterDados(json, RespostaGutendex.class);
        List<DadosLivro> livrosApi = resposta.getResults();

        if (livrosApi.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            ConverteLivro conversorLivro = new ConverteLivro();
            for (DadosLivro dados : livrosApi) {
                Livro livro = conversorLivro.converter(dados);
                System.out.println(livro);
            }
        }
    }

    private String listaLivrosResgritrados() {
        return "Livros registrados";
    }

    private String listaAutoresResgritrados() {
        return "Autores registrados";
    }

    private String autoresVivosEmAno() {
        return "Autores vivos em ano X";
    }

    private String livrosEmIdioma() {
        return "Livros em idioma X";
    }


}

