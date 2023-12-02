package Controllers;

import DAO.AgendamentoDAO;
import DAO.UsuarioDAO;
import Helpers.EditaUsuarioHelper;
import Helpers.UsuarioLogado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.RollbackException;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Agendamento;
import models.NivelAcesso;
import models.Usuario;
import org.hibernate.exception.ConstraintViolationException;
import views.secundarias.EditaUsuario;

/**
 *
 * @author igor
 */
public class EditaUsuarioController {
   private final EditaUsuario view;
   private final EntityManager em;
   private final UsuarioDAO DAO;
   private final EditaUsuarioHelper helper;

    public EditaUsuarioController(EditaUsuario view, EntityManager em) {
        this.view = view;
        this.em = em;
        DAO = new UsuarioDAO(em);
        helper = new EditaUsuarioHelper(view);
        
        helper.verTabelaUsuarios();
        helper.habilitaDeletaUsuarioENivelAcesso();
        preencherCampos();
        atualizaTabela();
        
        try { view.setMaximum(true); } catch (PropertyVetoException ex) { 
            Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);}
    }
   
    public void preencherCampos(){
        int id = UsuarioLogado.getInstance().getIdBarbeiro();
        
        try {
           Usuario usuario = DAO.selectById(id);
           view.getTxtNome().setText(usuario.getNome());
           view.getTxtCpf().setText(usuario.getCpf());
           view.getTxtTelefone().setText(usuario.getTelefone());
           view.getTxtPerguntaRecuperacao().setText(usuario.getPerguntaSeguranca());
           view.getTxtRespostaRecuperacao().setText(usuario.getRespostaSeguranca());
           view.getTxtSenha().setText(usuario.getSenha());
           
        } catch (SQLException ex) {
           Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editaDadosUsuario(){
        try {
            int id;
            // Verifica se o usuário logado é um adm
            if(UsuarioLogado.getInstance().userIsAdm()){
                int linhaTabela = view.getTabelaUsuarios().getSelectedRow();
                id = Integer.parseInt(view.getTabelaUsuarios().getValueAt(linhaTabela, 0).toString());
                
            }else{
                id = UsuarioLogado.getInstance().getIdBarbeiro();
            }
            
            if (view.getTxtNome().getText().isEmpty() || view.getTxtCpf().getText().isEmpty() ||
                view.getTxtTelefone().getText().isEmpty() || view.getTxtPerguntaRecuperacao().getText().isEmpty() ||
                view.getTxtRespostaRecuperacao().getText().isEmpty() ||  view.getTxtSenha().getText().isEmpty()) 
            {
              throw new IllegalArgumentException("Não deixe nenhum campo vazio.");  
            }
           
            int opc = JOptionPane.showConfirmDialog(view, "Deseja editar os dados?", "Editar dados", JOptionPane.YES_NO_OPTION);
            if(opc == JOptionPane.YES_OPTION){
                Usuario usuario = DAO.selectById(id);
                usuario.setNome(view.getTxtNome().getText());
                usuario.setCpf(view.getTxtCpf().getText());
                usuario.setTelefone(view.getTxtTelefone().getText());
                usuario.setPerguntaSeguranca(view.getTxtPerguntaRecuperacao().getText());
                usuario.setRespostaSeguranca(view.getTxtRespostaRecuperacao().getText());
                usuario.setSenha(view.getTxtSenha().getText());
                
                NivelAcesso nivelAcesso = NivelAcesso.stringEnum(view.getCbxNivelAcesso().getSelectedItem().toString());
                usuario.setNivelAcesso(nivelAcesso);
                atualizaTabela();

                DAO.update(usuario);
                helper.resultOperacao("Dados atualizados.", "Os dados de "+ usuario.getNome() + "\nforam atualizados.", false);
                
            }else{
                helper.resultOperacao("Operação Cancelada","Operação Cancelada.", true);
            }
            
        } catch (SQLException ex) {
           System.out.println("SISTEMA: " + ex.getMessage());
           Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex){
           helper.resultOperacao(ex.getMessage(), ex.getMessage(), false);
        }
    }
    
    public void deletaUsuario(){
        try {
            Usuario usuario = DAO.selectByNome(view.getTxtNome().getText());
            
            if (usuario == null){
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            
            String mensagemAgendamentos = "";
            if (qtdAgendamentosMarcados(usuario.getId())> 0){
                mensagemAgendamentos = "Agendamentos marcados: " + qtdAgendamentosMarcados(usuario.getId()) + "\n";
            }
            
            int opc = JOptionPane.showConfirmDialog(view, mensagemAgendamentos + "Deseja deletar esse Usuário?\n\nUsuário: " + usuario.getNome(), "Deletar Usuário", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION){
                deletaAgendamentos(usuario.getId());
                DAO.delete(usuario);
                atualizaTabela();
                helper.resultOperacao("Usuário deletado com sucesso.", "Usuário deletado com sucesso.", true);
            }else{
                helper.resultOperacao("Operação Cancelada","Operação Cancelada.", false);
            }
            
       } catch (SQLException ex) {
           Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IllegalArgumentException ex){
           helper.resultOperacao(ex.getMessage(), ex.getMessage(), false);
       } catch (ConstraintViolationException | RollbackException ex){
           helper.resultOperacao("Erro ao excluir.", "Erro ao excluir.", false);
       }
    }
    
    public void atualizaTabela(){ 
        List<Usuario> usuarios = DAO.selectAll();

        DefaultTableModel tableModel = (DefaultTableModel)view.getTabelaUsuarios().getModel();
        tableModel.setNumRows(0);

        for (Usuario usuario: usuarios) {
            tableModel.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getNivelAcesso().getDescricao(),
            });
        }
    }   
    
    public void completaDados(){
        int linhaTabela = view.getTabelaUsuarios().getSelectedRow();
        String nome = view.getTabelaUsuarios().getValueAt(linhaTabela, 1).toString();
       
            Usuario usuario = new UsuarioDAO(em).selectByNome(nome); 
            
            view.getTxtNome().setText(usuario.getNome());
            view.getTxtCpf().setText(usuario.getCpf());
            view.getTxtTelefone().setText(usuario.getTelefone());
            view.getTxtPerguntaRecuperacao().setText(usuario.getPerguntaSeguranca());
            view.getTxtRespostaRecuperacao().setText(usuario.getRespostaSeguranca());
            view.getTxtSenha().setText(usuario.getSenha());
            view.getCbxNivelAcesso().setSelectedItem(usuario.getNivelAcesso().getDescricao());
    }
    
    // Retornar a quantidade de agendamentos marcados caso exista
    private int qtdAgendamentosMarcados(Integer usuarioId){
        List<Agendamento> agendamentos = new AgendamentoDAO(em).selectAllbyBarbeiro(usuarioId);
        return agendamentos.size();
    }
    
    // Deletar os agendamentos do usuario
    private void deletaAgendamentos(Integer usuarioId){
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(em);
        List<Agendamento> agendamentos = agendamentoDAO.selectAllbyBarbeiro(usuarioId);
        
        for (Agendamento agendamento: agendamentos){
            try {
                agendamentoDAO.delete(agendamento);
                
            } catch (SQLException ex) {
            Logger.getLogger(EditaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
