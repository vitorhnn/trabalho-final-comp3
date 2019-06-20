package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto;

import org.jetbrains.annotations.Contract;

public final class UsuarioDTO {
    private int id;

    private String cpf;

    private String nome;

    private String senha;

    @Contract(pure = true)
    private UsuarioDTO(int id, String cpf, String nome, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    @Contract(pure = true)
    public int getId() {
        return id;
    }

    @Contract(pure = true)
    public String getCpf() {
        return cpf;
    }

    @Contract(pure = true)
    public String getNome() {
        return nome;
    }

    @Contract(pure = true)
    public String getSenha() {
        return senha;
    }

    public static class UsuarioDTOBuilder {
        private int id;
        private String cpf;
        private String nome;
        private String senha;

        public UsuarioDTOBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UsuarioDTOBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public UsuarioDTOBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public UsuarioDTOBuilder setSenha(String senha) {
            this.senha = senha;
            return this;
        }

        public UsuarioDTO build() {
            return new UsuarioDTO(id, cpf, nome, senha);
        }
    }
}
