package gerenciadorTarefas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import gerenciadorTarefas.model.Tarefa;
import gerenciadorTarefas.service.TarefaService;

@Named("tarefaController")
@ViewScoped
public class TarefaController implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private TarefaService tarefaService;

    private List<Tarefa> tarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;
    private List<String> listaResponsaveis;
    private List<String> listaPrioridades;
    private String filtroNumero;
    private String filtroTitulo;
    private String filtroSituacao;
    private String filtroResponsavel;
    private String idParam;
	  


		public String getIdParam() {
			return idParam;
		}


		public void setIdParam(String idParam) {
			this.idParam = idParam;
		}


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
	        this.listaPrioridades = Arrays.asList("Alta", "Média", "Baixa");
	        this.listaResponsaveis = Arrays.asList("Joana", "Mariana", "Pedro");
	        try {
	            this.tarefas = tarefaService.listarTodos();
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar dados: " + e.getMessage(), null));
	        }
	        
	        String idParam = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap()
                    .get("id");
	        
	        if (idParam != null && !idParam.isEmpty()) {
	            try {
	                Long id = Long.parseLong(idParam);
	                this.tarefaSelecionada = tarefaService.buscarPorId(id);
	            } catch (NumberFormatException e) {
	                FacesContext.getCurrentInstance().addMessage(null, 
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID de tarefa inválido.", null));
	            }
	        }  else {
                this.tarefaSelecionada = new Tarefa();
            }
		}
	    
		public String prepararCadastro() {
	        return "cadastro?faces-redirect=true";
	    }
		
	    public String save() {
	        tarefaService.salvar(tarefaSelecionada);
	        return "lista-tarefas?faces-redirect=true";
	    }

	    public String excluir(Tarefa tarefa) {
	        try {
	            tarefaService.excluir(tarefa.getId());
	            this.tarefas = tarefaService.listarTodos();

	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa excluída com sucesso!", null));
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir tarefa: " + e.getMessage(), null));
	        }

	      return "lista-tarefas?faces-redirect=true";
	    }

	    public String concluir(Tarefa tarefa) {
	        tarefaService.concluir(tarefa.getId());
	        this.tarefas = tarefaService.listarTodos();
	        
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarefa concluída com sucesso!", null));
	            
	       return "lista-tarefas?faces-redirect=true";
	    }
	   
	    public String listar() {
	    	this.tarefas = tarefaService.listarTodos();
	    	return "lista-tarefas?faces-redirect=true";
	    }

	    public void carregarTarefa() {
	        if (tarefaSelecionada != null && tarefaSelecionada.getId() != null) {
	            tarefaSelecionada = tarefaService.buscarPorId(tarefaSelecionada.getId());
	        }
	    }
	  
	    public void buscar() {
	    	if((filtroNumero == null || filtroNumero.trim().isEmpty()) &&
    	        (filtroTitulo == null || filtroTitulo.trim().isEmpty()) &&
    	        (filtroSituacao == null || filtroSituacao.trim().isEmpty()) &&
    	        (filtroResponsavel == null || filtroResponsavel.trim().isEmpty()))
    	    {
	    		this.tarefas = tarefaService.listarTodos();
	    	} else {
	    		this.tarefas = tarefaService.buscar(filtroResponsavel, filtroTitulo, filtroSituacao, filtroNumero);
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




