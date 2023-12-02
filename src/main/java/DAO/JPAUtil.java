package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

    // Retorna um Entity Manager de Conexao com o banco de dados
    public EntityManager getEntityManager() {
            return emf.createEntityManager();
    }
}
