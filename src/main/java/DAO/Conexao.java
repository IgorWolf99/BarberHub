package DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {
    
        public static void iniciarConexao(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
            EntityManager em = emf.createEntityManager();
        }

    public Conexao() {
    }
        
        
}
