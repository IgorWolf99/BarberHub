package Controllers;

import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import Helpers.DadosClienteHelper;
import Helpers.UsuarioLogado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.Agendamento;
import models.Cliente;
import views.secundarias.DadosCliente;

public class DadosClienteController {
    private final DadosCliente view;
    private final EntityManager em;
    private final ClienteDAO DAO;
    private final DadosClienteHelper helper;
    
    public DadosClienteController(DadosCliente view, EntityManager em) {
        this.view = view;
        this.em = em;
        this.DAO = new ClienteDAO(em);
        this.helper = new DadosClienteHelper(view);
                
        totalClientes();
        atualizaTabelaClientes();
        adicionarDocumentListener(view.getBtnAtualizar());
        adicionarDocumentListener(view.getBtnDeletar());
        adicionarDocumentListener(view.getBtnLimparCampos());
        
        try { view.setMaximum(true); } catch (PropertyVetoException ex) { 
            Logger.getLogger(DadosClienteController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    /*  Realiza uma consulta JPQL para encontrar clientes cujos nomes comecem com a letra
      digitada no campo cliente. A consulta nao diferencia letras maiúsculas e minúsculas. */
    public void pesquisarCliente(){
        String nomeDigitado = view.getTxtNome().getText();

        // Consulta personalizada por letra do nome
        List<Cliente> clientes = DAO.pesquisarClientesPorLetra(nomeDigitado);
        
        // Adicionar resultados na tabela
        DefaultTableModel tableModel = (DefaultTableModel) view.getTabelaClientes().getModel();
        tableModel.setNumRows(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        for(Cliente cliente : clientes){
            tableModel.addRow(new Object[]{
                cliente.getNome(),
                dateFormat.format(cliente.getNasc()),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getCpf(),
                cliente.getObservacao()
            });  
        }
    }
    
    public void atualizaDados() {
    try {
        Cliente cliente = DAO.selectById(Integer.parseInt(view.getTxtId().getText()));
        
        helper.verificaCamposVazios();
        
        int opc = JOptionPane.showConfirmDialog(view,"Deseja Atualizar os dados?\nUsuário: " + cliente.getNome(),
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if(opc == JOptionPane.YES_OPTION) {
            
                cliente.setNome(view.getTxtNome().getText());
                cliente.setTelefone(view.getTxtTelefone().getText());
                cliente.setCpf(view.getTxtCpf().getText());
                cliente.setEndereco(view.getTxtEndereco().getText());
                cliente.setNasc(helper.converterData(view.getTxtNasc().getText()));
                cliente.setObservacao(view.getTxtObservacao().getText());
                DAO.update(cliente);
                
                helper.resultOperacao("Dados Atualizados.", "Dados Atualizados.", false);
        }else{
            helper.resultOperacao("Operação Cancelada.", "Operação Cancelada.", false);
            }
        atualizaTabelaClientes();
       
    } catch (SQLException ex) {
        System.out.println("SISTEMA: Erro ao atualizar dados.");
        Logger.getLogger(DadosClienteController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalArgumentException ex) {
        helper.resultOperacao(ex.getMessage(), ex.getMessage(), false);
    } catch (PersistenceException ex) {
        helper.resultOperacao("Data inválida!\nVerifique se o campo 'Data' está preenchido corretamente.",
                "Data inválida!\nVerifique se o campo 'Data' está preenchido corretamente.", false);
    }
}
     
    public void deletaDados(){
        try {
            //Verificando se o usuário é um adm
            if(!UsuarioLogado.getInstance().userIsAdm()){
                helper.resultOperacao("Usuario logado não possui acesso a função deletar.",
                        "Apenas o Administrador pode deletar o cadastro de clientes.", false);
            } else {
                
            // Id do cliente
            int id = Integer.parseInt(view.getTxtId().getText());
            
            Cliente cliente = DAO.selectById(id);
            
            String mensagemAgendamentos = "";
            
            if (DAO.agendamentosPendentes(cliente.getId()) > 0) {
                mensagemAgendamentos = "Esse cliente possui: " + DAO.agendamentosPendentes(cliente.getId()) + " agendamento(s) pendentes.\n";
            }
            int resultado = JOptionPane.showConfirmDialog(view, mensagemAgendamentos + "Deseja apagar os dados desse cliente?\n\nCliente: " + cliente.getNome(), "Confirmação", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                deletaAgendamentos(cliente.getId());
                DAO.delete(cliente);
                helper.resultOperacao("Dados Apagados\nUsuário " + cliente.getNome() + " excluido.",
                        "Dados Apagados\nCliente " + cliente.getNome() + " excluido.", true);
            } else {
                helper.resultOperacao("Operação Cancelada","Operação Cancelada.", true);
            }
            atualizaTabelaClientes();
            }
        } catch (SQLException ex) {
            System.out.println("SISTEMA: Erro ao apagar os dados.");
            Logger.getLogger(DadosClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void completarDados(){
        int linhaTabela = view.getTabelaClientes().getSelectedRow();
       
        String nome = view.getTabelaClientes().getModel().getValueAt(linhaTabela, 0).toString();
        
        try {
            Cliente c1 = DAO.selectByNome(nome);
       
            view.getTxtId().setText(String.valueOf(c1.getId()));
            view.getTxtNome().setText(nome);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            view.getTxtNasc().setText(dateFormat.format(c1.getNasc()));
            view.getTxtTelefone().setText(c1.getTelefone());
            view.getTxtEndereco().setText(c1.getEndereco());
            view.getTxtCpf().setText(c1.getCpf());
            view.getTxtObservacao().setText(c1.getObservacao());
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: ERRO");
            Logger.getLogger(DadosClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaTabelaClientes(){
        List<Cliente> clientes = DAO.selectAllEmOrdem();
        
        DefaultTableModel tableModel = (DefaultTableModel)view.getTabelaClientes().getModel();
        tableModel.setNumRows(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        for(Cliente cliente : clientes){
            tableModel.addRow(new Object[]{
                cliente.getNome(),
                cliente.getTelefone(),
                dateFormat.format(cliente.getNasc()),
                cliente.getEndereco(),
                cliente.getCpf(),
                cliente.getObservacao()
            });  
        }
    }
    
    public void limparCampos(){
        helper.limparCampos();
        atualizaTabelaClientes();
    }
    
    public void totalClientes(){
     List<Cliente> dados = DAO.selectAll();
     view.getLblTotalClientes().setText(dados.size() + " Clientes");
    }
    
    // Deletar os agendamentos do usuario que tenha agendamentos
    private void deletaAgendamentos(Integer clienteId){
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(em);
        List<Agendamento> agendamentos = agendamentoDAO.selectAllbyCliente(clienteId);
        
        for (Agendamento agendamento: agendamentos){
            try {
                agendamentoDAO.delete(agendamento);
                
            } catch (SQLException ex) {
            Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    // DocumentListener para ativar os botões Atualizar/Deletar caso tenha um item selecionado
    private void adicionarDocumentListener(JButton button) {
        view.getTxtId().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarBotao(button);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarBotao(button);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarBotao(button);
            }
        });
    }

    private void atualizarBotao(JButton button) {
        String texto = view.getTxtId().getText();
        button.setEnabled(!texto.isEmpty());
    }

}
