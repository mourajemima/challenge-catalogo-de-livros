package dev.jemima.catalogodelivros.model;

import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private Integer anoNascimento;
    private Integer anoMorte;
    private List<String> idiomas;
    private Integer numeroDownloads;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
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
                "\nAutor: " + autor + " (" + anoNascimento + " - " + anoMorte + ")" +
                "\nIdiomas: " + idiomas +
                "\nDownloads: " + numeroDownloads +
                "\n------";
    }
}
