package gerenciadorTarefas.dao;

import gerenciadorTarefas.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void adicionar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, responsavel, prioridade, deadline, situacao) VALUES (?, ?, ?, ?, ?, ?)";
        try (java.sql.Connection conn = (java.sql.Connection) ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getResponsavel());
            stmt.setString(4, tarefa.getPrioridade());
            stmt.setObject(5, tarefa.getDeadline());
            stmt.setString(6, tarefa.getSituacao());
            stmt.executeUpdate();
            
            System.out.println("Tarefa adicionada com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    public Tarefa buscarPorId(Long id) {
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Tarefa tarefa = null;
        try (java.sql.Connection conn = (java.sql.Connection) ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa();
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setResponsavel(rs.getString("responsavel"));
                    tarefa.setPrioridade(rs.getString("prioridade"));
                    tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
                    tarefa.setSituacao(rs.getString("situacao"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefa por ID: " + e.getMessage());
        }
        return tarefa;
    }

    public List<Tarefa> listarTodos() {
        String sql = "SELECT * FROM tarefas";
        List<Tarefa> tarefas = new ArrayList<>();
        try (java.sql.Connection conn = (java.sql.Connection) ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setResponsavel(rs.getString("responsavel"));
                tarefa.setPrioridade(rs.getString("prioridade"));
                tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
                tarefa.setSituacao(rs.getString("situacao"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todas as tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo=?, descricao=?, responsavel=?, prioridade=?, deadline=?, situacao=? WHERE id=?";
        try (java.sql.Connection conn = (java.sql.Connection) ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getResponsavel());
            stmt.setString(4, tarefa.getPrioridade());
            stmt.setObject(5, tarefa.getDeadline());
            stmt.setString(6, tarefa.getSituacao());
            stmt.setLong(7, tarefa.getId());
            stmt.executeUpdate();

            System.out.println("Tarefa atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM tarefas WHERE id=?";
        try (java.sql.Connection conn = (java.sql.Connection) ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

            System.out.println("Tarefa excluída com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }
    
    public List<Tarefa> buscaTitulo(String texto) {
        String sql = "SELECT * FROM tarefas WHERE LOWER(titulo) LIKE LOWER(?)";
        List<Tarefa> tarefas = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + texto + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                 
                    tarefa.setId(rs.getLong("id"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por título: " + e.getMessage());
        }
        return tarefas;
    }
    
    
    public List<Tarefa> buscaSituacao(String situacao) {
        String sql = "SELECT * FROM tarefas WHERE situacao = ?";
        List<Tarefa> tarefas = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, situacao.toUpperCase());
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setResponsavel(rs.getString("responsavel"));
                    tarefa.setPrioridade(rs.getString("prioridade"));
                    tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
                    tarefa.setSituacao(rs.getString("situacao"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por situação: " + e.getMessage());
        }
        return tarefas;
    }

    public List<Tarefa> buscaResponsavel(String responsavel) {
        if (responsavel == null || responsavel.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String sql = "SELECT * FROM tarefas WHERE responsavel = ?";
        List<Tarefa> tarefas = new ArrayList<>();

        try (
            Connection conn = ConnectionFactory.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, responsavel);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setResponsavel(rs.getString("responsavel"));
                    tarefa.setPrioridade(rs.getString("prioridade"));
                    tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
                    tarefa.setSituacao(rs.getString("situacao"));
                    tarefas.add(tarefa);
                }
            }
        
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por responsável: " + e.getMessage());
            throw new RuntimeException("Erro na busca por responsável", e);
        }

        return tarefas;
    }


}