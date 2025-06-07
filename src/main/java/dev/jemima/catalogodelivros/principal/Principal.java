package dev.jemima.catalogodelivros.principal;

import dev.jemima.catalogodelivros.model.*;
import dev.jemima.catalogodelivros.service.ConsumoApi;
import dev.jemima.catalogodelivros.service.ConverteDados;
import dev.jemima.catalogodelivros.service.ConverteLivro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi api = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private List<Livro> livrosBuscados = new ArrayList<>();
    private List<Autor> autoresRegistrados = new ArrayList<>();

    public void menu() throws IOException, InterruptedException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ***** MENU *****
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores resgistrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair             
                    ****************                  
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
        System.out.println("\nBusque um livro pelo título: ");
        var livroBusca = scanner.nextLine();
        var json = api.consomeApi(ENDERECO + livroBusca.replace(" ", "%20"));
        RespostaGutendex resposta = conversor.obterDados(json, RespostaGutendex.class);
        List<DadosLivro> livrosApi = resposta.getResults();

        if (livrosApi.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            ConverteLivro conversorLivro = new ConverteLivro();
            DadosLivro dados = livrosApi.get(0); // apenas o primeiro resultado
            Livro livro = conversorLivro.converter(dados);
            System.out.println(livro);
            livrosBuscados.add(livro);

            var autor = livro.getAutor(); // 👈 único autor
            if (autor != null && !autoresRegistrados.contains(autor)) {
                autoresRegistrados.add(autor);
            }
        }
    }

    private void listaLivrosResgritrados() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum livro foi registrado ainda.");
        } else {
            for (Livro livro : livrosBuscados) {
                System.out.println(livro);
            }
        }
    }

    private void listaAutoresResgritrados() {
        if (autoresRegistrados.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            System.out.println("\nAutores registrados:");
            for (Autor autor : autoresRegistrados) {
                System.out.println(autor);
            }
        }
    }


    private void autoresVivosEmAno() {
        System.out.print("Digite o ano para verificar autores vivos: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> vivos = autoresRegistrados.stream()
                .filter(a -> a.getAnoNascimento() != null && a.getAnoNascimento() <= ano)
                .filter(a -> a.getAnoMorte() == null || a.getAnoMorte() > ano)
                .toList();

        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano informado.");
        } else {
            System.out.println("Autores vivos em " + ano + ":");
            vivos.forEach(a -> System.out.println(a.getNome()));
        }
    }


    private void livrosEmIdioma() {
        System.out.println("Informe o idioma (ex: en, pt, fr): ");
        String idioma = scanner.nextLine();

        List<Livro> livrosFiltrados = livrosBuscados.stream()
                .filter(l -> l.getIdioma().equalsIgnoreCase(idioma))
                .toList();

        if (livrosFiltrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado nesse idioma.");
        } else {
            livrosFiltrados.forEach(System.out::println);
        }
    }


}

