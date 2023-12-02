package Controllers;

import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import DAO.ServicoDAO;
import Helpers.RegistroAgendamentoHelper;
import Helpers.UsuarioLogado;
import jakarta.persistence.EntityManager;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.Agendamento;
import models.Cliente;
import models.Servico;
import views.secundarias.RegistroAgendamento;

/**
 *
 * @author igor
 */
public class RegistroAgendamentoController {
    private final RegistroAgendamento view;
    private final EntityManager em;
    private final AgendamentoDAO DAO;
    private final RegistroAgendamentoHelper helper;

    public RegistroAgendamentoController(RegistroAgendamento view, EntityManager em) {
        this.view = view;
        this.em = em;
        DAO = new AgendamentoDAO(em);
        helper = new RegistroAgendamentoHelper(view);
        
        atualizaTabela();
        atualizaTabelaTodosAgendamentos();
        atualizaServico();
        lblQtdAgendamentos();
        
        view.getLblFinalizado().setVisible(false);
        view.getCbFinalizado().setVisible(false);
        
        adicionarDocumentListener(view.getBtnAtualizar());
        adicionarDocumentListener(view.getBtnDeletar());
        adicionarDocumentListener(view.getBtnLimparCampos());
        adicionarDocumentListener(view.getBtnFinalizarAgendamento());
        
        try { view.setMaximum(true); } catch (PropertyVetoException ex) { 
            Logger.getLogger(RegistroAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    public void editaAgendamento(){
        try {
            Agendamento agendamento = DAO.selectById(Integer.parseInt(view.getTxtId().getText()));
            
            if(view.getTxtHorario().getText().isEmpty()|| view.getTxtValor().getText().isEmpty()){
                throw new IllegalArgumentException("Não deixe campos vazios.");
            }
            
            int result = JOptionPane.showConfirmDialog(view, "Deseja editar o agendamento?\nUsuario: "       
                        + agendamento.getCliente().getNome(), "Editar Agendamento", JOptionPane.YES_NO_OPTION); 
            
            if(result == JOptionPane.YES_OPTION){
                
                agendamento.setServico(new ServicoDAO(em).selectByDesc(view.getCbxServico().getSelectedItem().toString()));
                agendamento.setData(helper.converterStringDate(view.getTxtHorario().getText()));
                String valor = view.getTxtValor().getText().replace(",",".");
                agendamento.setValor(Double.valueOf(valor));
                agendamento.setObservacao(view.getTxtObservacao().getText());
                DAO.update(agendamento);
                
                atualizaTabela();
                System.out.println("SISTEMA: Dados Atualizados");
                helper.resultOperacao("Dados atualizados", "O agendamento de "+agendamento.getCliente().getNome()+"\n foi atualizado.", false);
                
            } else{
                helper.resultOperacao("Operação cancelada.", "Operação cancelada.", false);
            }      

        } catch (SQLException ex) {
            System.out.println("SISTEMA: Erro ao atualizar dados");
            Logger.getLogger(RegistroAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex){
            helper.resultOperacao("SISTEMA: Valor Inválido || Valor: "+ view.getTxtValor().getText(),
                    "Valor inválido!\nVerifique se o campo 'Valor' está preenchido corretamente.", false);
        } catch (IllegalArgumentException ex){
            helper.resultOperacao(ex.getMessage(), ex.getMessage(), false);
        }
    }
    
    public void deletaAgendamento(){
        try {
            //Busca um agendamento utilizando o id que consta no campo ID
            Agendamento agendamento = DAO.selectById(Integer.parseInt(view.getTxtId().getText()));
            
            int result = JOptionPane.showConfirmDialog(view, "Deseja cancelar o agendamento?\n\nCliente: "       
                        + agendamento.getCliente().getNome(), "Cancelar Agendamento", JOptionPane.YES_NO_OPTION); 
            
            if(result == JOptionPane.YES_OPTION){
                DAO.delete(agendamento);
                
                helper.resultOperacao("Agendamento de " + agendamento.getCliente().getNome() + " deletado", 
                        "Agendamento de"  +  agendamento.getCliente().getNome() + " deletado.", true);
                
            } else{
                helper.resultOperacao("Operação Cancelada", "Operação Cancelada.", false);
            }
            
            atualizaTabela();
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: ERRO");
            Logger.getLogger(RegistroAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finalizarAgendamento(){
        try {
            Agendamento agendamento = DAO.selectById(Integer.parseInt(view.getTxtId().getText()));
            
            if (agendamento.isFinalizado()){
                helper.resultOperacao("Agendamento já finalizado.", "Agendamento já finalizado.", false);
                } else {

                int result = JOptionPane.showConfirmDialog(view, "Deseja finalizar esse agendamento?\n\nUsuario: "       
                            + agendamento.getCliente().getNome(), "Finalizar Agendamento", JOptionPane.YES_NO_OPTION); 

                if(result == JOptionPane.YES_OPTION){

                    agendamento.setFinalizado(true);
                    DAO.update(agendamento);
                    helper.resultOperacao("Agendamento finalizado.", "Agendamento finalizado.", false);

                } else{
                    helper.resultOperacao("Operação cancelada.", "Operação cancelada.", false);
                }  

                atualizaTabela();
                helper.lblAgendamentoFinalizado(agendamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaTabela(){ 
        List<Agendamento> agendamentos = DAO.selectAllbyBarbeiro(UsuarioLogado.getInstance().getIdBarbeiro());

        DefaultTableModel tableModel = (DefaultTableModel)view.getTabelaClientes().getModel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
        tableModel.setNumRows(0);

        for (Agendamento agendamento : agendamentos) {
            tableModel.addRow(new Object[]{
                dateFormat.format(agendamento.getData()),
                agendamento.getCliente().getNome(),
                agendamento.stringFinalizado(),
                agendamento.getServico().getServico(),
                String.format("%.2f", agendamento.getValor()),
                agendamento.getObservacao()
                    
            });
            lblQtdAgendamentos();
    }
}   
    
    public void completaDados(){
        int linhaTabela = view.getTabelaClientes().getSelectedRow();
        String horario = view.getTabelaClientes().getValueAt(linhaTabela, 0).toString();
        String nome = view.getTabelaClientes().getValueAt(linhaTabela, 1).toString();
       
        try {
            Cliente cliente = new ClienteDAO(em).selectByNome(nome); 
            
            // Busca no banco uma marcação de um cliente x horario
            Agendamento agendamento = DAO.selectByClienteHorario(cliente,helper.converterStringDate(horario));
                      
            view.getTxtCliente().setText(agendamento.getCliente().getNome());
            view.getTxtHorario().setText(new SimpleDateFormat("dd/MM - HH:mm").format(agendamento.getData()));
            view.getCbxServico().getModel().setSelectedItem(agendamento.getServico().getServico());
            view.getTxtId().setText(String.valueOf(agendamento.getId()));
            view.getTxtValor().setText(String.format("%.2f", agendamento.getValor()).replace(".",","));
            view.getTxtObservacao().setText(agendamento.getObservacao());
            
            helper.lblAgendamentoFinalizado(agendamento);
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: ERRO");
            Logger.getLogger(RegistroAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            System.out.println("SISTEMA: Erro ao converter a data.");
        }
    }
    
    public void atualizaTabelaTodosAgendamentos(){ 
        atualizaTabela();
        List<Agendamento> agendamentos = DAO.selectAllEmOrdem();
        
        DefaultTableModel tableModel = (DefaultTableModel)view.getTabelaTodosAgendamentos().getModel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
        tableModel.setNumRows(0);
        
        for (Agendamento agendamento : agendamentos){
            tableModel.addRow(new Object[]{
                agendamento.getBarbeiro().getNome(),
                dateFormat.format(agendamento.getData()),
                agendamento.stringFinalizado(),
                agendamento.getCliente().getNome(),
                agendamento.getServico().getServico(),
                String.format("%.2f", agendamento.getValor()),
                agendamento.getObservacao(),
        });
        }
        
    }
    
    public void lblQtdAgendamentos(){
     //Label quantidade agendamentos
     List<Agendamento> dados = DAO.selectAllbyBarbeiro(UsuarioLogado.getInstance().getBarbeiro().getId());
     view.getLblTotalAgendamentos().setText("Agendamentos:  " + dados.size());
     
     //Label todos agendamentos
     List<Agendamento> agendamentos = DAO.selectAll();
     view.getLblTodosAgendamentos().setText("Agendamentos:  " + agendamentos.size());
    }
    
    public void atualizaServico(){
        List<Servico> servicos = new ServicoDAO(em).selectAll();
        DefaultComboBoxModel cbxModel = (DefaultComboBoxModel)view.getCbxServico().getModel();
        for (Servico servico : servicos){
            cbxModel.addElement(servico);
        }
    }
    
    public void limparCampos(){
        helper.limparCampos();
        atualizaTabela();
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
