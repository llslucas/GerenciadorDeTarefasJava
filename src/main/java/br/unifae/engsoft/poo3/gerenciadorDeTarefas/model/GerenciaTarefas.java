package br.unifae.engsoft.poo3.gerenciadorDeTarefas.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class GerenciaTarefas {

  private static List<Tarefa> tarefas = null;

  public GerenciaTarefas() {
    if (tarefas == null) {
      tarefas = new ArrayList<>();
    }
  }

  public boolean create(Tarefa tarefa) {
    if (tarefa == null) {
      return false;
    }

    if (tarefas.contains(tarefa)) {
      return false;
    }

    return tarefas.add(tarefa);
  }
  
  public boolean update(int position, Tarefa tarefa){
    if(tarefa == null){
      return false;
    }
    
    if(position < 0 || position > tarefas.size()){
      return false;
    }
    
    return tarefas.set(position, tarefa) != null;
  }

  public List<Tarefa> list() {
    return tarefas;
  }

  public boolean delete(int position) {
    if (position < 0 || position > tarefas.size()) {
      return false;
    }

    return tarefas.remove(position) != null;
  }
}
