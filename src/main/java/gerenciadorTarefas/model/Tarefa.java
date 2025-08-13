package gerenciadorTarefas.model;

import java.time.LocalDate;
import java.util.Objects;


public class Tarefa {
	    private Long id;
	    private String titulo;
	    private String descricao;
	    private String responsavel;
	    private String prioridade;
	    private LocalDate deadline;
	    private String situacao = "EM ANDAMENTO";
	    
	    

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

	    public Tarefa() {
	    };


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

		@Override
		public int hashCode() {
			return Objects.hash(deadline, descricao, id, prioridade, responsavel, situacao, titulo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tarefa other = (Tarefa) obj;
			return Objects.equals(deadline, other.deadline) && Objects.equals(descricao, other.descricao)
					&& Objects.equals(id, other.id) && Objects.equals(prioridade, other.prioridade)
					&& Objects.equals(responsavel, other.responsavel) && Objects.equals(situacao, other.situacao)
					&& Objects.equals(titulo, other.titulo);
		}

		@Override
		public String toString() {
			return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", responsavel="
					+ responsavel + ", prioridade=" + prioridade + ", deadline=" + deadline + ", situacao=" + situacao
					+ "]";
		}
			
				

}


