package dev.jemima.catalogodelivros.service;

import dev.jemima.catalogodelivros.model.Autor;
import dev.jemima.catalogodelivros.model.DadosLivro;
import dev.jemima.catalogodelivros.model.Livro;

public class ConverteLivro {

    public Livro converter(DadosLivro dadosLivro) {
        var livro = new Livro();
        livro.setTitulo(dadosLivro.tituloLivro());

        if (dadosLivro.authors() != null && !dadosLivro.authors().isEmpty()) {
            var autorDados = dadosLivro.authors().get(0);
            Autor autor = new Autor(
                    autorDados.nomeAutor(),
                    autorDados.anoNascimento(),
                    autorDados.anoMorte()
            );
            livro.setAutor(autor);
        } else {
            livro.setAutor(new Autor("Autor desconhecido", null, null));
        }

        if (dadosLivro.languages() != null && !dadosLivro.languages().isEmpty()) {
            livro.setIdioma(dadosLivro.languages().get(0));
        }

        livro.setNumeroDownloads(dadosLivro.downloads());

        return livro;
    }

}
