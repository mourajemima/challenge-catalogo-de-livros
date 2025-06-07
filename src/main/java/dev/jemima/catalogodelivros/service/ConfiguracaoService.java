package dev.jemima.catalogodelivros.service;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfiguracaoService {

    public static void acessaVariaveis(){
        Dotenv dotenv = Dotenv.load();

        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }
}
