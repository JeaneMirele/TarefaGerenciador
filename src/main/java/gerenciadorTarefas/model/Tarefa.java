package gerenciadorTarefas.model;

import java.time.LocalDate;


public class Tarefa {
	    private Long id;
	    private String titulo;
	    private String descricao;
	    private String responsavel;
	    private String prioridade;
	    private LocalDate deadline;
	    private String situacao;
	    
	    

	    public Tarefa(Long id, String titulo, String descricao, String responsavel, String prioridade,
				LocalDate deadline, String situacao) {
			super();
			this.id = id;
			this.titulo = titulo;
			this.descricao = descricao;
			this.responsavel = responsavel;
			this.prioridade = prioridade;
			this.deadline = deadline;
			this.situacao = situacao;
		}



		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getTitulo() {
			return titulo;
		}



		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}



		public String getDescricao() {
			return descricao;
		}



		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}



		public String getResponsavel() {
			return responsavel;
		}



		public void setResponsavel(String responsavel) {
			this.responsavel = responsavel;
		}



		public String getPrioridade() {
			return prioridade;
		}



		public void setPrioridade(String prioridade) {
			this.prioridade = prioridade;
		}



		public LocalDate getDeadline() {
			return deadline;
		}



		public void setDeadline(LocalDate deadline) {
			this.deadline = deadline;
		}



		public String getSituacao() {
			return situacao;
		}



		public void setSituacao(String situacao) {
			this.situacao = situacao;
		}



		public Tarefa() {
	        this.situacao = "Em andamento";
	    }

}


