package Helpers;

import DAO.UsuarioDAO;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.NivelAcesso;
import models.Usuario;


public class PrimeiroLogin {
    private final EntityManager em;

    public PrimeiroLogin(EntityManager em) {
        this.em = em;
    }
    
    public void usuarioPrimeiroLogin(){
            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            
        try {
            Usuario user = new Usuario("ADMIN", "xxx", "xxx", "admin", "Admin?", "Admin", NivelAcesso.ADMINISTRADOR);
            
            List<Usuario> usuarios = usuarioDAO.selectAll();
            boolean exist = false;
            for (Usuario usuario : usuarios){
                if("ADMIN".equals(usuario.getNome())){
                    exist = true;
                }
            }
            if (exist == false){
             usuarioDAO.insert(user);
             new PovoarDB().povoarDB(em);
             System.out.println("SISTEMA: CRIADO USUARIO 'ADMIN' PARA O PRIMEIRO LOGIN");
            }
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: ERRO AO CRIAR USUARIO 'ADMIN' PARA O PRIMEIRO LOGIN");
            Logger.getLogger(PrimeiroLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
