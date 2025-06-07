package dev.jemima.catalogodelivros.service;

import dev.jemima.catalogodelivros.model.DadosLivro;
import dev.jemima.catalogodelivros.model.Livro;

public class ConverteLivro {

    public Livro converter(DadosLivro dadosLivro) {
        var livro = new Livro();
        livro.setTitulo(dadosLivro.tituloLivro());

        if (dadosLivro.authors() != null && !dadosLivro.authors().isEmpty()) {
            var autor = dadosLivro.authors().get(0);
            livro.setAutor(autor.nomeAutor());
            livro.setAnoNascimento(autor.anoNascimento());
            livro.setAnoMorte(autor.anoMorte());
        } else {
            livro.setAutor("Autor desconhecido");
        }

        livro.setIdiomas(dadosLivro.languages());
        livro.setNumeroDownloads(dadosLivro.downloads());

        return livro;
    }
}
