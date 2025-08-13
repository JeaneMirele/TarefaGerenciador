package gerenciadorTarefas.dao;

	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/tarefas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "98680730";


    public static Connection connect(){
        Connection conn = null;
        try{
        	Class.forName("org.postgresql.Driver"); 
            conn = (Connection) DriverManager.getConnection(ConnectionFactory.URL, ConnectionFactory.USER, ConnectionFactory.PASSWORD);
            if (conn != null) {
                System.out.println("Connected to database");
            }
        } catch (SQLException e) {
            System.out.println("Error in connection: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return conn;
    }

}



