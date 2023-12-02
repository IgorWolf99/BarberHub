package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;


public class ClienteDAO {
    EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Cliente cliente) throws SQLException{
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }
    
    public void update(Cliente cliente)throws SQLException{
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }
    
    public void delete(Cliente cliente)throws SQLException{
        em.getTransaction().begin();
        em.merge(cliente);
        em.remove(cliente);
        em.getTransaction().commit();
    }
    
    public Cliente selectById(int id)throws SQLException{
       return em.find(Cliente.class, id);
    }
    
    public Cliente selectByNome(String nome) throws SQLException {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }
    
    public List<Cliente> pesquisarClientesPorLetra(String nomeDigitado){
        String jpql = "SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(:nome)";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("nome", nomeDigitado + "%");

        return query.getResultList();
    }
    
    public Long agendamentosPendentes(int id){
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(a) FROM Agendamento a WHERE a.cliente.id = :clienteId AND a.finalizado = false",
            Long.class );
        query.setParameter("clienteId", id);

        return query.getSingleResult();
    }
    
    public List<Cliente> selectAllEmOrdem(){
        String jpql = "SELECT c FROM Cliente c ORDER BY c.nome ASC";
        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
        
    }
    
    public List<Cliente> selectAll(){
         String jpql = "select c from Cliente as c";
        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
   
    }

     private List<Cliente> retornarListaComBaseNaConsulta(Query query) {
        List<Cliente> clientes;
        try{
            clientes = query.getResultList();
            
        }catch(NoResultException e){
            clientes = new ArrayList<Cliente>();
        }
        return clientes;
    }
    
}
