package br.unifae.engsoft.poo3.gerenciadorDeTarefas.controller;

import br.unifae.engsoft.poo3.gerenciadorDeTarefas.model.GerenciaTarefas;
import br.unifae.engsoft.poo3.gerenciadorDeTarefas.model.Tarefa;
import br.unifae.engsoft.poo3.gerenciadorDeTarefas.model.TarefaComPrazo;
import br.unifae.engsoft.poo3.gerenciadorDeTarefas.model.TarefaSimples;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class TarefasController {

  GerenciaTarefas gerenciaTarefas = null;

  public TarefasController() {
    gerenciaTarefas = new GerenciaTarefas();
  }

  public boolean createTarefa(String descricao, int prioridade, String dataCriacao, String prazo) {
    if (prazo.equals("  /  /    ") || prazo.equals("")) {
      return createTarefaSimples(descricao, prioridade, dataCriacao);
    } else {
      return createTarefaComPrazo(descricao, prioridade, dataCriacao, prazo);
    }
  }

  private boolean createTarefaComPrazo(String descricao, int prioridade, String dataCriacao, String prazo) {
    try {
      LocalDate dataCriacaoDate = LocalDate.parse(
	      dataCriacao,
	      DateTimeFormatter.ofPattern("dd/MM/yyyy")
      );

      LocalDate prazoDate = LocalDate.parse(
	      prazo,
	      DateTimeFormatter.ofPattern("dd/MM/yyyy")
      );

      TarefaComPrazo tarefa = new TarefaComPrazo(
	      descricao,
	      prioridade,
	      dataCriacaoDate,
	      prazoDate
      );

      return gerenciaTarefas.create(tarefa);

    } catch (java.time.format.DateTimeParseException error) {
      JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  private boolean createTarefaSimples(String descricao, int prioridade, String dataCriacao) {
    try {
      LocalDate dataCriacaoDate = LocalDate.parse(
	      dataCriacao,
	      DateTimeFormatter.ofPattern("dd/MM/yyyy")
      );

      TarefaSimples tarefa = new TarefaSimples(descricao, prioridade, dataCriacaoDate);

      return gerenciaTarefas.create(tarefa);
    } catch (java.time.format.DateTimeParseException error) {
      JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  public void preencherTabela(JTable tabela) {
    DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
    tabela.setModel(tableModel);
    tabela.selectAll();
    tableModel.setRowCount(0);

    for (int i = 0; i < gerenciaTarefas.list().size(); i++) {
      Tarefa tarefa = gerenciaTarefas.list().get(i);

      Object[] data = {
	String.valueOf(i),
	tarefa.getDescricao(),
	tarefa.getPrioridade(),
	tarefa.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
	tarefa instanceof TarefaComPrazo ? ((TarefaComPrazo) tarefa).getPrazo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null,
	tarefa.isConcluida(),
      };

      tableModel.addRow(data);
    }

    tableModel.addTableModelListener(evento -> {
      if (evento.getType() == TableModelEvent.UPDATE) {
	this.update(tabela);
      }
    });
  }

  public void update(JTable tabela) {
    if (tabela.getSelectedRow() == -1) {
      return;
    }

    int linha = tabela.getSelectedRow();
    Tarefa tarefa = gerenciaTarefas.list().get(linha);
    tarefa.setConcluida((boolean) tabela.getValueAt(linha, 5));

    gerenciaTarefas.update(linha, tarefa);

    System.out.println("Tarefa atualizada");
  }

  public void delete(JTable tabela) {
    if (tabela.getSelectedRow() == -1) {
      JOptionPane.showMessageDialog(
	      null,
	      "Nenhuma tarefa selecionada.",
	      "Aviso",
	      0
      );

      return;
    }

    int resposta = JOptionPane.showConfirmDialog(
	    null,
	    "Tem certeza que deseja excluir esta tarefa?",
	    "Aviso",
	    JOptionPane.YES_NO_OPTION
    );

    if (resposta == JOptionPane.YES_OPTION) {
      gerenciaTarefas.delete(tabela.getSelectedRow());
      JOptionPane.showMessageDialog(null, "Produto Excluido com sucesso!", "Aviso", 1);
    }
  }
}
