package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto;

import org.jetbrains.annotations.Contract;

public final class UsuarioDTO {
    private int id;

    private String cpf;

    private String nome;

    private String senha;

    private String museu;

    private String cargo;

    @Contract(pure = true)
    private UsuarioDTO(int id, String cpf, String nome, String senha, String museu, String cargo) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.museu = museu;
        this.cargo = cargo;
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

    @Contract(pure = true)
    public String getMuseu() {
        return museu;
    }

    @Contract(pure = true)
    public String getCargo() {
        return cargo;
    }

    public static class UsuarioDTOBuilder {
        private int id;
        private String cpf;
        private String nome;
        private String senha;
        private String museu;
        private String cargo;

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

        public UsuarioDTOBuilder setMuseu(String museu) {
            this.museu = museu;
            return this;
        }

        public UsuarioDTOBuilder setCargo(String cargo) {
            this.cargo = cargo;
            return this;
        }

        public UsuarioDTO build() {
            return new UsuarioDTO(id, cpf, nome, senha, museu, cargo);
        }
    }
}