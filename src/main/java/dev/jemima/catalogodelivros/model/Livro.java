package dev.jemima.catalogodelivros.model;

import java.util.List;

public class Livro {
    private String titulo;
    private Autor autor;
    private String idioma;
    private Integer numeroDownloads;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "\n------" +
                "\nLIVRO" +
                "\nTitulo: " + titulo +
                "\nAutor: " + (autor != null ? autor : "Autor desconhecido") +
                "\nIdioma: " + idioma +
                "\nDownloads: " + numeroDownloads +
                "\n------";
    }
}
