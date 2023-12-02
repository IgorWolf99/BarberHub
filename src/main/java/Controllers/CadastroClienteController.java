package Controllers;

import DAO.ClienteDAO;
import Helpers.CadastroClienteHelper;
import jakarta.persistence.EntityManager;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cliente;
import views.secundarias.CadastroCliente;


public class CadastroClienteController {
    private final CadastroCliente view;
    private final EntityManager em;
    private final ClienteDAO DAO;
    private final CadastroClienteHelper helper;
    
    public CadastroClienteController(CadastroCliente view, EntityManager em) {
        this.view = view;
        this.em = em;
        this.DAO = new ClienteDAO(em);
        this.helper= new CadastroClienteHelper(view);
        
        try { view.setMaximum(true);} catch (PropertyVetoException ex) {
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public void cadastrarUsuario(){
        try {
            String nome = view.getTxtNome().getText();
            String telefone = view.getTxtTelefone().getText();
            String endereco = view.getTxtEndereco().getText();
            Date nasc = helper.converterStringDate(view.getTxtNasc().getText());       
            String cpf = view.getTxtCpf().getText();
            String observacao = view.getTxtObservacao().getText();
            if (nome.isEmpty() || endereco.isEmpty() || view.getTxtNasc().getText().isEmpty() || cpf.isEmpty()){
                throw new IllegalArgumentException("Campos obrigatórios não podem estar vazios.");
            }
           
            Cliente cliente = new Cliente(nome, nasc, endereco, telefone, cpf, observacao);

            DAO.insert(cliente);
            helper.resultOperacao("Cliente adicionado com sucesso.", "Cliente adicionado com sucesso.", true);
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);      
            System.out.println("SISTEMA: Erro ao adicionar novo cliente");
        } catch (IllegalArgumentException ex){
            helper.resultOperacao(ex.getMessage(), ex.getMessage(), false);
        }
    }
}
