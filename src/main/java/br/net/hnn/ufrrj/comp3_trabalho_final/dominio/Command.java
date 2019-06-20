package br.net.hnn.ufrrj.comp3_trabalho_final.dominio;

public interface Command<T, E extends Exception> {
    T execute() throws E;
}
