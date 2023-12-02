package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Agendamento;
import models.Cliente;

/**
 *
 * @author igor
 */
public class AgendamentoDAO {
     EntityManager em; 
     
    public AgendamentoDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Agendamento agendamento) throws SQLException{
        em.getTransaction().begin();
        em.persist(agendamento);
        em.getTransaction().commit();
    }
    
    public void update(Agendamento agendamento)throws SQLException{
        em.getTransaction().begin();
        em.merge(agendamento);
        em.persist(agendamento);
        em.getTransaction().commit();
    }
    
    public void delete(Agendamento agendamento)throws SQLException{
        em.getTransaction().begin();
        em.merge(agendamento);
        em.remove(agendamento);
        em.getTransaction().commit();
    }
    
    public Agendamento selectById(int id)throws SQLException{
       return em.find(Agendamento.class, id);
    }
 
    public Agendamento selectByClienteHorario(Cliente cliente, Date horario) throws SQLException, ParseException {
        TypedQuery<Agendamento> query = em.createQuery("SELECT a FROM Agendamento a WHERE a.cliente = :cliente AND a.horario = :horario", Agendamento.class);
        query.setParameter("cliente", cliente);
        query.setParameter("horario", horario);
        return query.getSingleResult();
    }
    
    public List<Agendamento> selectAllEmOrdem(){
        String jpql = "SELECT a FROM Agendamento a ORDER BY a.horario ASC";

        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
    }
    
    public List<Agendamento> selectAll(){
        String jpql = "select c from Agendamento as c";
        Query query = em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
    }
    
    public List<Agendamento> selectAllbyBarbeiro(Integer id){
        String jpql = "SELECT a FROM Agendamento a WHERE a.barbeiro.id = :barbeiroId ORDER BY a.horario ASC"; 
        Query query = em.createQuery(jpql);
        query.setParameter("barbeiroId", id);
        return retornarListaComBaseNaConsulta(query);
    }
    
    public List<Agendamento> selectAllbyCliente(Integer id){
        String jpql = "SELECT a FROM Agendamento a WHERE a.cliente.id = :clienteId"; 
        Query query = em.createQuery(jpql);
        query.setParameter("clienteId", id);
        return retornarListaComBaseNaConsulta(query);
    }

    public List<Agendamento> selectAllbyServico(Integer id){
        String jpql = "SELECT a FROM Agendamento a WHERE a.servico.id = :servicoId"; 
        Query query = em.createQuery(jpql);
        query.setParameter("servicoId", id);
        return retornarListaComBaseNaConsulta(query);
    }
    
    private List<Agendamento> retornarListaComBaseNaConsulta(Query query) {
       List<Agendamento> agendamentos;
       try{
           agendamentos = query.getResultList();

       }catch(NoResultException e){
           agendamentos = new ArrayList<Agendamento>();
       }
       return agendamentos;
    }
    
}
