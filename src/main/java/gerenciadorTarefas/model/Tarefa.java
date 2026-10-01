package gerenciadorTarefas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(length = 255)
    private String descricao;

    @Column(length = 100)
    private String responsavel;

    @Column(length = 50)
    private String prioridade;

    @Column
    private LocalDate deadline;

    @Column(length = 50)
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


