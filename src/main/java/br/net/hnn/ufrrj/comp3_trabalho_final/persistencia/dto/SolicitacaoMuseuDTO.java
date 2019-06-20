package br.net.hnn.ufrrj.comp3_trabalho_final.persistencia.dto;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public final class SolicitacaoMuseuDTO {
    private int id;

    private String nome;

    private LocalDate dataCriacao;

    private String cidade;

    private String estado;

    private String cpfGestor;

    private String nomeGestor;

    private String senhaGestor;

    private SolicitacaoMuseuDTO(int id, String nome, LocalDate dataCriacao, String cidade, String estado, String cpfGestor, String nomeGestor, String senhaGestor) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.cidade = cidade;
        this.estado = estado;
        this.cpfGestor = cpfGestor;
        this.nomeGestor = nomeGestor;
        this.senhaGestor = senhaGestor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCpfGestor() {
        return cpfGestor;
    }

    public String getNomeGestor() {
        return nomeGestor;
    }

    public String getSenhaGestor() {
        return senhaGestor;
    }

    public static class SolicitacaoMuseuDTOBuilder {
        private int id;
        private String nome;
        private LocalDate dataCriacao;
        private String cidade;
        private String estado;
        private String cpfGestor;
        private String nomeGestor;
        private String senhaGestor;

        public SolicitacaoMuseuDTOBuilder() {
        }

        public SolicitacaoMuseuDTOBuilder(@NotNull SolicitacaoMuseuDTO dto) {
            this.id = dto.getId();
            this.nome = dto.getNome();
            this.dataCriacao = dto.getDataCriacao();
            this.cidade = dto.getCidade();
            this.estado = dto.getEstado();
            this.cpfGestor = dto.getCpfGestor();
            this.nomeGestor = dto.getNomeGestor();
            this.senhaGestor = dto.getSenhaGestor();
        }

        public SolicitacaoMuseuDTOBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setDataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setEstado(String estado) {
            this.estado = estado;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setCpfGestor(String cpfGestor) {
            this.cpfGestor = cpfGestor;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setNomeGestor(String nomeGestor) {
            this.nomeGestor = nomeGestor;
            return this;
        }

        public SolicitacaoMuseuDTOBuilder setSenhaGestor(String senhaGestor) {
            this.senhaGestor = senhaGestor;
            return this;
        }

        public SolicitacaoMuseuDTO build() {
            return new SolicitacaoMuseuDTO(id, nome, dataCriacao, cidade, estado, cpfGestor, nomeGestor, senhaGestor);
        }
    }
}
