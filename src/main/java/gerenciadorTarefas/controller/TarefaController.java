package gerenciadorTarefas.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import gerenciadorTarefas.dao.TarefaDAO;
import gerenciadorTarefas.model.Tarefa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tarefaController")
@ViewScoped
public class TarefaController {
	
		private TarefaDAO daoTarefa;
	    private List<Tarefa> tarefas;
	    private Tarefa tarefaSelecionada;
	    private List<String> listaResponsaveis;
	    private List<String>  listaPrioridades;
	    
	    @PostConstruct
	    public void init() {
	        this.daoTarefa = new TarefaDAO();
	        this.tarefaSelecionada = new Tarefa();
	        this.listaPrioridades = Arrays.asList("Alta", "Média", "Baixa");
	        try {
	            this.tarefas = daoTarefa.listarTodos();
	            this.listaResponsaveis = daoTarefa.listarResponsaveis();
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar dados: " + e.getMessage(), null));
	        }
	    }


	    public String save() {
	        if (tarefaSelecionada.getId() == null) {
	        	daoTarefa.adicionar(tarefaSelecionada);
	        } else {
	        	daoTarefa.atualizar(tarefaSelecionada);
	        }
	        return "lista-tarefas?faces-redirect=true";
	    }

	    public String excluir(){
	    	daoTarefa.excluir(tarefaSelecionada.getId());
	        FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa excluída com sucesso!", null)
	        );
	        return "lista-tarefas?faces-redirect=true";

	    }

	    public String editar() {
	    	daoTarefa.atualizar(tarefaSelecionada);
	        this.tarefas = daoTarefa.listarTodos();
	        return "lista-tarefas?faces-redirect=true";
	    }

	    public String concluir(){
	        tarefaSelecionada.setSituacao("CONCLUIDA");
	        daoTarefa.atualizar(tarefaSelecionada);
	        return "lista-tarefas?faces-redirect=true";
	    }

		public List<Tarefa> getTarefas() {
			return tarefas;
		}

		public void setTarefas(List<Tarefa> tarefas) {
			this.tarefas = tarefas;
		}

		public Tarefa getTarefaSelecionada() {
			return tarefaSelecionada;
		}

		public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
			this.tarefaSelecionada = tarefaSelecionada;
		}

		public List<String> getListaResponsaveis() {
			return listaResponsaveis;
		}

		public void setListaResponsaveis(List<String> listaResponsaveis) {
			this.listaResponsaveis = listaResponsaveis;
		}

		public List<String> getListaPrioridades() {
			return listaPrioridades;
		}

		public void setListaPrioridades(List<String> listaPrioridades) {
			this.listaPrioridades = listaPrioridades;
		}
	    

	}




