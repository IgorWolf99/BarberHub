package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Servico;


public class ServicoDAO {
    EntityManager em;

    public ServicoDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Servico servico) throws SQLException{
        em.getTransaction().begin();
        em.persist(servico);
        em.getTransaction().commit();
    }
    
    public void update(Servico servico)throws SQLException{
        em.getTransaction().begin();
        em.merge(servico);
        em.persist(servico);
        em.getTransaction().commit();
    }
    
    public void delete(Servico servico)throws SQLException{
        em.getTransaction().begin();
        em.merge(servico);
        em.remove(servico);
        em.getTransaction().commit();
    }
    
    public Servico selectById(int id)throws SQLException{
       return em.find(Servico.class, id);
    }
    
    public Servico selectByDesc(String nome) throws SQLException {
        TypedQuery<Servico> query = em.createQuery("SELECT c FROM Servico c WHERE c.descricao = :descricao", Servico.class);
        query.setParameter("descricao", nome);
        return query.getSingleResult();
    }
    
    public List<Servico> selectAll(){
         String jpql = "select c from Servico as c";
        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
   
    }

    public Long agendamentosPendentes(int id){
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(a) FROM Agendamento a WHERE a.servico.id = :servicoId AND a.finalizado = false",
            Long.class );
        query.setParameter("servicoId", id);
        System.out.println("---------------" + query.getSingleResult());
        return query.getSingleResult();
    }
    
    private List<Servico> retornarListaComBaseNaConsulta(Query query) {
        List<Servico> servicos;
        try{
            servicos = query.getResultList();
            
        }catch(NoResultException e){
            servicos = new ArrayList<Servico>();
        }
        return servicos;
    }
    
}
