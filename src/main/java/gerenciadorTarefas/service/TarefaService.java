package gerenciadorTarefas.service;

import gerenciadorTarefas.model.Tarefa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class TarefaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "tarefasPU")
    private EntityManager em;

    public void salvar(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            em.persist(tarefa);
        } else {
            em.merge(tarefa);
        }
    }

    public Tarefa buscarPorId(Long id) {
        return em.find(Tarefa.class, id);
    }

    public List<Tarefa> listarTodos() {
        return em.createQuery("SELECT t FROM Tarefa t ORDER BY t.id ASC", Tarefa.class)
                 .getResultList();
    }

    public void excluir(Long id) {
        Tarefa tarefa = em.find(Tarefa.class, id);
        if (tarefa != null) {
            em.remove(tarefa);
        }
    }

    public void concluir(Long id) {
        Tarefa tarefa = em.find(Tarefa.class, id);
        if (tarefa != null) {
            tarefa.setSituacao("CONCLUIDA");
            em.merge(tarefa);
        }
    }

    public List<Tarefa> buscar(String responsavel, String titulo, String situacao, String numero) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tarefa> cq = cb.createQuery(Tarefa.class);
        Root<Tarefa> root = cq.from(Tarefa.class);

        List<Predicate> predicates = new ArrayList<>();

        if (responsavel != null && !responsavel.trim().isEmpty()) {
            predicates.add(cb.equal(root.get("responsavel"), responsavel.trim()));
        }

        if (titulo != null && !titulo.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("titulo")), "%" + titulo.trim().toLowerCase() + "%"));
        }

        if (situacao != null && !situacao.trim().isEmpty()) {
            predicates.add(cb.equal(root.get("situacao"), situacao.trim()));
        }

        if (numero != null && !numero.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(numero.trim());
                predicates.add(cb.equal(root.get("id"), id));
            } catch (NumberFormatException ignored) {
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        cq.orderBy(cb.asc(root.get("id")));

        TypedQuery<Tarefa> query = em.createQuery(cq);
        return query.getResultList();
    }
}
