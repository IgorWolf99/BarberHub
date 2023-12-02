package Helpers;

import jakarta.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igorw
 */
public class PovoarDB {
    
    public void povoarDB(EntityManager em){
        
        String caminho = "script_sql.txt";
        
        try {
            String script = new String(Files.readAllBytes(Paths.get(caminho)));  
            
            em.getTransaction().begin();
            em.createNativeQuery(script).executeUpdate();
            em.getTransaction().commit();
            System.out.println("SISTEMA: Banco Povoado com sucesso");
        
        } catch (FileNotFoundException ex) {
            System.out.println("ERRO: " + ex.getClass().getSimpleName().toUpperCase());
            Logger.getLogger(PovoarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("ERRO: " + ex.getClass().getSimpleName().toUpperCase());
            Logger.getLogger(PovoarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
