package br.unifae.engsoft.poo3.gerenciadorDeTarefas.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Lucas
 */
public abstract class Tarefa {

  private String descricao;
  private boolean concluida;
  private int prioridade;
  private LocalDate dataCriacao;

  public Tarefa() {
  }

  public Tarefa(String descricao, int prioridade, LocalDate dataCriacao) {
    this.descricao = descricao;
    this.prioridade = prioridade;
    this.dataCriacao = dataCriacao;
    this.concluida = false;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public boolean isConcluida() {
    return concluida;
  }

  public void setConcluida(boolean concluida) {
    this.concluida = concluida;
  }

  public int getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(int prioridade) {
    this.prioridade = prioridade;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @Override
  public String toString() {
    return String.format(
	    "Tarefa: Descricao: %s, Concluida: %s, Prioridade: %s, dataCriacao: %s",
	    descricao, concluida, prioridade, dataCriacao
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }

    if (!(obj instanceof Tarefa)) {
      return false;
    }

    if (!(this.descricao.equals(((Tarefa) obj).descricao))) {
      return false;
    }

    if ((this.prioridade != ((Tarefa) obj).prioridade)) {
      return false;
    }

    if ((this.concluida != ((Tarefa) obj).concluida)) {
      return false;
    }

    return this.dataCriacao.equals(((Tarefa) obj).dataCriacao);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.descricao);
    hash = 97 * hash + (this.concluida ? 1 : 0);
    hash = 97 * hash + this.prioridade;
    hash = 97 * hash + Objects.hashCode(this.dataCriacao);
    return hash;
  }
}
