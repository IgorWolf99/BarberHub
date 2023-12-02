package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Usuario;

public class UsuarioDAO {
    EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Usuario usuario) throws SQLException{
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }
    
    public void update(Usuario usuario)throws SQLException{
        em.getTransaction().begin();
        em.merge(usuario);
        em.persist(usuario);
        em.getTransaction().commit();
    }
    
    public void delete(Usuario usuario)throws SQLException{
        em.getTransaction().begin();
        em.merge(usuario);
        em.remove(usuario);
        em.getTransaction().commit();
    }
    
    public Usuario selectById(int id)throws SQLException{
       return em.find(Usuario.class, id);
    }
    
    public Usuario selectByNome(String nome){
         String jpql = "SELECT u FROM Usuario u WHERE u.nome = :nome";
         Query query = em.createQuery(jpql);
         query.setParameter("nome", nome);
         
         List<Usuario> usuarios = query.getResultList();
         
         for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
            return usuario;
        } 
     }
        return null;    
    }
    
    public Usuario usuarioNomeSenha(String nome, String senha){
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha");
        query.setParameter("nome", nome);
        query.setParameter("senha", senha);

        List<Usuario> result = query.getResultList();

        // Se o resultado não estiver vazio, o usuário foi autenticado
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    
    public String perguntaSeguranca(int id) {
        Query query = em.createQuery("SELECT u.perguntaSeguranca FROM Usuario u WHERE u.id = :id");
        query.setParameter("id", id);
        return query.getSingleResult().toString();
    }
    
    public Usuario respostaSeguranca(String nome, String respostaSeguranca) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE UPPER(u.nome) = UPPER(:nome) AND UPPER(u.respostaSeguranca) = UPPER(:respostaSeguranca)");
        query.setParameter("nome", nome);
        query.setParameter("respostaSeguranca", respostaSeguranca);

        List<Usuario> result = query.getResultList();

        // Se o resultado não estiver vazio, o usuário foi autenticado
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
        
    }
    
    public List<Usuario> selectAll(){
         String jpql = "select c from Usuario as c";
        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
    }

     private List<Usuario> retornarListaComBaseNaConsulta(Query query) {
        List<Usuario> usuarios;
        try{
            usuarios = query.getResultList();
            
        }catch(NoResultException e){
            usuarios = new ArrayList<Usuario>();
        }
        return usuarios;
    }
     
     
}