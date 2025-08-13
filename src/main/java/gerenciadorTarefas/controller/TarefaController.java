package gerenciadorTarefas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import gerenciadorTarefas.dao.TarefaDAO;
import gerenciadorTarefas.model.Tarefa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tarefaController")
@ViewScoped
public class TarefaController implements Serializable {
    private static final long serialVersionUID = 1L;
	
		private TarefaDAO daoTarefa = new TarefaDAO();
	    private List<Tarefa> tarefas = new ArrayList<>();
	    private Tarefa tarefaSelecionada = new Tarefa();
	    private List<String> listaResponsaveis;
	    private List<String>  listaPrioridades;
	    private String filtroNumero;
	    private String filtroTitulo;
	    private String filtroSituacao;
	    private String filtroResponsavel;
	    
	  


		public String getFiltroNumero() {
			return filtroNumero;
		}


		public void setFiltroNumero(String filtroNumero) {
			this.filtroNumero = filtroNumero;
		}


		public String getFiltroTitulo() {
			return filtroTitulo;
		}


		public void setFiltroTitulo(String filtroTitulo) {
			this.filtroTitulo = filtroTitulo;
		}


		public String getFiltroSituacao() {
			return filtroSituacao;
		}


		public void setFiltroSituacao(String filtroSituacao) {
			this.filtroSituacao = filtroSituacao;
		}


		public String getFiltroResponsavel() {
			return filtroResponsavel;
		}


		public void setFiltroResponsavel(String filtroResponsavel) {
			this.filtroResponsavel = filtroResponsavel;
		}


		@PostConstruct
	    public void init() {
			
			this.daoTarefa = new TarefaDAO();
	        this.listaPrioridades = Arrays.asList("Alta", "Média", "Baixa");
	        this.listaResponsaveis = Arrays.asList("Joana", "Mariana", "Pedro");
	        try {
	            this.tarefas = daoTarefa.listarTodos();
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar dados: " + e.getMessage(), null));
	        }
		}
	    

		public String prepararCadastro() {
	        this.tarefaSelecionada = new Tarefa();
	        return "cadastro?faces-redirect=true";
	    }
		
		
	    public String save() {
	        if (tarefaSelecionada.getId() == null) {
	        	daoTarefa.adicionar(tarefaSelecionada);
	        } else {
	        	daoTarefa.atualizar(tarefaSelecionada);
	        }
	        return "lista-tarefas?faces-redirect=true";
	    }

	    

	    public String editar(Tarefa tarefa) {
	        this.tarefaSelecionada = tarefa;
	        return "cadastro?faces-redirect=true";
	    }

	
	    public String excluir(Tarefa tarefa) {
	        try {
	            daoTarefa.excluir(tarefa.getId());
	            this.tarefas = daoTarefa.listarTodos();

	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa excluída com sucesso!", null));
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir tarefa: " + e.getMessage(), null));
	        }

	      return "lista-tarefas?faces-redirect=true";
	    }

	 
	    public String concluir(Tarefa tarefa) {
	        tarefa.setSituacao("CONCLUIDA");
	        daoTarefa.atualizar(tarefa);
	        
	     
	        this.tarefas = daoTarefa.listarTodos();
	        
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa concluída com sucesso!", null));
	            
	       return "lista-tarefas?faces-redirect=true";
	    }
	    

	   
	    public String listar() {
	    	daoTarefa.listarTodos();
	    	return "lista-tarefas?faces-redirect=true";
	    }

	    public void carregarTarefa() {
	        if (tarefaSelecionada != null && tarefaSelecionada.getId() != null) {
	            tarefaSelecionada = daoTarefa.buscarPorId(tarefaSelecionada.getId());
	        }
	    }
	  
	    public void buscar() {
	    	
	    	if((filtroNumero == null || filtroNumero.trim().isEmpty()) &&
    	        (filtroTitulo == null || filtroTitulo.trim().isEmpty()) &&
    	        (filtroSituacao == null || filtroSituacao.trim().isEmpty()) &&
    	        (filtroResponsavel == null || filtroResponsavel.trim().isEmpty()))
    	    {
	    		this.tarefas = daoTarefa.listarTodos();
	    	}else {
	    		this.tarefas = daoTarefa.buscar(filtroResponsavel, filtroTitulo, filtroSituacao,filtroNumero);
	    	}     
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


		@Override
		public int hashCode() {
			return Objects.hash(filtroNumero, filtroResponsavel, filtroSituacao, filtroTitulo, listaPrioridades,
					listaResponsaveis, tarefaSelecionada, tarefas);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TarefaController other = (TarefaController) obj;
			return Objects.equals(filtroNumero, other.filtroNumero)
					&& Objects.equals(filtroResponsavel, other.filtroResponsavel)
					&& Objects.equals(filtroSituacao, other.filtroSituacao)
					&& Objects.equals(filtroTitulo, other.filtroTitulo)
					&& Objects.equals(listaPrioridades, other.listaPrioridades)
					&& Objects.equals(listaResponsaveis, other.listaResponsaveis)
					&& Objects.equals(tarefaSelecionada, other.tarefaSelecionada)
					&& Objects.equals(tarefas, other.tarefas);
		}


}




