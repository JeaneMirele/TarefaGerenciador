package gerenciadorTarefas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
		private TarefaDAO daoTarefa;
	    private List<Tarefa> tarefas;
	    private Tarefa tarefaSelecionada;
	    private List<String> listaResponsaveis;
	    private List<String>  listaPrioridades;
	    private String buscaNumero;
	    private String buscaTitulo;
	    private String buscaSituacao;

	    public TarefaController() {};
	    
	    public String getBuscaNumero() {
			return buscaNumero;
		}


		public void setBuscaNumero(String buscaNumero) {
			this.buscaNumero = buscaNumero;
		}


		public String getBuscaTitulo() {
			return buscaTitulo;
		}


		public void setBuscaTitulo(String buscaTitulo) {
			this.buscaTitulo = buscaTitulo;
		}


		public String getBuscaSituacao() {
			return buscaSituacao;
		}


		public void setBuscaSituacao(String buscaSituacao) {
			this.buscaSituacao = buscaSituacao;
		}


		@PostConstruct
	    public void init() {
			this.daoTarefa = new TarefaDAO();
	        this.tarefaSelecionada = new Tarefa();
	        this.listaPrioridades = Arrays.asList("Alta", "Média", "Baixa");
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

	    public String excluir() {
	        if (tarefaSelecionada == null || tarefaSelecionada.getId() == null) {
	            FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	                    "Selecione uma tarefa antes de excluir", null)
	            );
	            return null; 
	        }
	        
	        try {
	            daoTarefa.excluir(tarefaSelecionada.getId());
	            this.tarefas = daoTarefa.listarTodos(); 
	            
	            FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, 
	                    "Tarefa excluída com sucesso!", null)
	            );
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	                    "Erro ao excluir tarefa: " + e.getMessage(), null)
	            );
	        }
	        
	        return null; 
	        
	        
	    }


	    public String editar(Tarefa tarefa) {
	        this.tarefaSelecionada = tarefa;
	        return "editar?faces-redirect=true" + tarefa.getId();
	    }

	    public String concluir(Tarefa tarefa) {
	        this.tarefaSelecionada = tarefa;
	        tarefaSelecionada.setSituacao("CONCLUIDA");
	        daoTarefa.atualizar(tarefaSelecionada);
	        this.tarefas = daoTarefa.listarTodos();
	        return null;
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
	        
	        if ((buscaNumero == null || buscaNumero.isEmpty()) &&
	            (buscaTitulo == null || buscaTitulo.isEmpty()) &&
	            (buscaSituacao == null || buscaSituacao.isEmpty()) &&
	            (tarefaSelecionada.getResponsavel() == null || tarefaSelecionada.getResponsavel().isEmpty())) {
	            
	            this.tarefas = daoTarefa.listarTodos();
	            return;
	        }
	  
	        if (buscaNumero != null && !buscaNumero.isEmpty()) {
	            try {
	                Long id = Long.parseLong(buscaNumero);
	                Tarefa tarefa = daoTarefa.buscarPorId(id);
	                this.tarefas = tarefa != null ? Arrays.asList(tarefa) : new ArrayList<>();
	                return; 
	            } catch (NumberFormatException e) {
	                FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número inválido", null));
	                return;
	            }
	        }
	      
	        if (buscaTitulo != null && !buscaTitulo.isEmpty()) {
	            this.tarefas = daoTarefa.buscaTitulo(buscaTitulo);
	            return;
	        }

	        if (buscaSituacao != null && !buscaSituacao.isEmpty()) {
	            this.tarefas = daoTarefa.buscaSituacao(buscaSituacao);
	            return;
	        }

	 
	        if (tarefaSelecionada.getResponsavel() != null && !tarefaSelecionada.getResponsavel().isEmpty()) {
	            this.tarefas = daoTarefa.buscaResponsavel(tarefaSelecionada.getResponsavel());
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
	    

	}




