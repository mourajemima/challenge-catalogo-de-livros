package dev.jemima.catalogodelivros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String tituloLivro,
                         List<DadosAutor> authors,
                         List<String> languages,
                         @JsonAlias("download_count") Integer downloads) {
}
