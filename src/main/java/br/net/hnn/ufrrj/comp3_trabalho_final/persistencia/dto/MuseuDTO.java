package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto;

import org.jetbrains.annotations.Contract;

import java.time.LocalDate;

public final class MuseuDTO {
    private int id;

    private String nome;

    private LocalDate dataCriacao;

    private String cidade;

    private String estado;

    private int idGestor;

    @Contract(pure = true)
    private MuseuDTO(String nome, LocalDate dataCriacao, String cidade, String estado, int idGestor) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.cidade = cidade;
        this.estado = estado;
        this.idGestor = idGestor;
    }

    @Contract(pure = true)
    public int getId() {
        return id;
    }

    @Contract(pure = true)
    public String getNome() {
        return nome;
    }

    @Contract(pure = true)
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Contract(pure = true)
    public String getCidade() {
        return cidade;
    }

    @Contract(pure = true)
    public String getEstado() {
        return estado;
    }

    @Contract(pure = true)
    public int getIdGestor() {
        return idGestor;
    }

    public static class MuseuDTOBuilder {
        private int id;
        private String nome;
        private LocalDate dataCriacao;
        private String cidade;
        private String estado;
        private int idGestor;

        public MuseuDTOBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public MuseuDTOBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MuseuDTOBuilder setDataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public MuseuDTOBuilder setCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public MuseuDTOBuilder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public MuseuDTOBuilder setIdGestor(int idGestor) {
            this.idGestor = idGestor;
            return this;
        }

        public MuseuDTO build() {
            return new MuseuDTO(nome, dataCriacao, cidade, estado, idGestor);
        }
    }
}
