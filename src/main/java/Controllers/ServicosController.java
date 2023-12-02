package Controllers;

import DAO.AgendamentoDAO;
import DAO.ServicoDAO;
import Helpers.ServicosHelper;
import jakarta.persistence.EntityManager;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Agendamento;
import models.Servico;
import views.secundarias.Servicos;

/**
 *
 * @author igor
 */
public class ServicosController {
    private final Servicos view;
    private final EntityManager em;
    private final ServicoDAO DAO;
    private final ServicosHelper helper;
    
    public ServicosController(Servicos view, EntityManager em) {
        this.view = view;
        this.em = em;
        DAO = new ServicoDAO(em);
        helper = new ServicosHelper(view);
        
        try { view.setMaximum(true); } catch (PropertyVetoException ex) { 
            Logger.getLogger(DadosClienteController.class.getName()).log(Level.SEVERE, null, ex);}
        
    }
    public void novoServico(){
        try { 
            String descricao = view.getTxtNomeServico().getText();
            String valorTexto = view.getTxtValorNovoServico().getText().replace(",",".");
            
            //Verificando campos vazios e texto no 'valor'
            if (descricao.isEmpty() || valorTexto.isEmpty()){
                throw new IllegalArgumentException("Não deixe nenhum campo vazio");
            }
            if(!helper.isNumero(valorTexto)){
                throw new IllegalArgumentException("Valor inválido!\nVerifique se o campo 'Valor' está preenchido corretamente");
            }
            Double valor = Double.valueOf(valorTexto);

            Servico servico = new Servico(descricao, valor);
            
            DAO.insert(servico);
            
            helper.resultOperacao("Novo serviço adicionado com sucesso", "Serviço adicionado com sucesso",true);
            
            atualizaServicos();
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: ERRO");
            Logger.getLogger(ServicosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex){
            helper.resultOperacao(ex.getMessage(), ex.getMessage(),false);
        }
        
    }
    
    public void deletaServico(){
        try {
            String desc = view.getCbxServico().getSelectedItem().toString();
        
            Servico servico = DAO.selectByDesc(desc);
            
            String mensagemAgendamentos = "";
            if (DAO.agendamentosPendentes(servico.getId())> 0){
                mensagemAgendamentos = "Agendamentos marcados: " + DAO.agendamentosPendentes(servico.getId()) + "\n";
            }
            
            int opc = JOptionPane.showConfirmDialog(view, mensagemAgendamentos + "Deseja excluir esse serviço?\n\nServiço: " + desc, "Excluir Serviço", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION){    
                deletaAgendamentos(servico.getId());
                DAO.delete(servico);
                helper.resultOperacao("Serviço excluido com sucesso.", "Serviço excluido com sucesso.",true);
                
            }else{
                helper.resultOperacao("Operação cancelada.", "Operação cancelada.",false);
            }
            atualizaServicos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editaValor(){
        try {
            Servico servico = DAO.selectByDesc(view.getCbxServico().getSelectedItem().toString());
            int opc = JOptionPane.showConfirmDialog(view, "Deseja atualizar esse serviço?\nServiço: " + servico.getServico(), "Atualizar Serviço", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION){    
                
                helper.isNumero(view.getTxtValor().getText());
                servico.setValor(Double.valueOf(view.getTxtValor().getText().replace(",", ".")));
                DAO.update(servico);
                
                helper.resultOperacao("Serviço atualizado com sucesso", "Serviço atualizado com sucesso",false);
                
            }else{
                helper.resultOperacao("Operação cancelada", "Operação cancelada",false);
            }
            atualizaServicos();    
        
        } catch (SQLException ex) {
            Logger.getLogger(ServicosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex){
            helper.resultOperacao("Valor Inválido || Valor: "+ view.getTxtValor().getText(),
                    "Valor inválido!\nVerifique se o campo 'Valor' está preenchido corretamente.", false);
        }
    }
    
    public void atualizaServicos(){
        List<Servico> servicos = new ServicoDAO(em).selectAll();
        DefaultComboBoxModel cbxModel = (DefaultComboBoxModel)view.getCbxServico().getModel();
        
        // Limpa o modelo antes de adicionar os novos elementos
        cbxModel.removeAllElements();
        
        for (Servico servico : servicos){
            cbxModel.addElement(servico);
        }
    }
    
   public void atualizaValorServico() {
        Servico servico = helper.obterServico();
        if(servico != null)
            helper.setarValor(servico.getValor());
        
    }

         // Retornar a quantidade de agendamentos marcados caso exista
    private int qtdAgendamentosMarcados(Integer servicoId){
        List<Agendamento> agendamentos = new AgendamentoDAO(em).selectAllbyServico(servicoId);
        return agendamentos.size();
    }
    
    // Deletar os agendamentos do usuario
    private void deletaAgendamentos(Integer servicoId){
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(em);
        List<Agendamento> agendamentos = agendamentoDAO.selectAllbyServico(servicoId);
        
        for (Agendamento agendamento: agendamentos){
            try {
                agendamentoDAO.delete(agendamento);
                
            } catch (SQLException ex) {
            Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

}
