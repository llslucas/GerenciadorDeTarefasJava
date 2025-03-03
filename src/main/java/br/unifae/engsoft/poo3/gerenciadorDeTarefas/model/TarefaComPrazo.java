package br.unifae.engsoft.poo3.gerenciadorDeTarefas.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Lucas
 */
public class TarefaComPrazo extends Tarefa {

  private LocalDate prazo;

  public TarefaComPrazo() {
  }

  public TarefaComPrazo(
	  String descricao, int prioridade, LocalDate dataCriacao, LocalDate prazo
  ) {
    super(descricao, prioridade, dataCriacao);
    this.prazo = prazo;
  }

  public LocalDate getPrazo() {
    return prazo;
  }

  public void setPrazo(LocalDate prazo) {
    this.prazo = prazo;
  }

  @Override
  public String toString() {
    return String.format("%s, Prazo: %s", super.toString(), prazo);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TarefaComPrazo)) {
      return false;
    }

    if (!super.equals(obj)) {
      return false;
    }

    return this.prazo.equals(((TarefaComPrazo) obj).prazo);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + Objects.hashCode(this.prazo);
    return hash;
  }
}
