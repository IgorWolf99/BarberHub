package Controllers;

import DAO.UsuarioDAO;
import jakarta.persistence.EntityManager;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.NivelAcesso;
import models.Usuario;
import views.secundarias.NovoUsuario;

/**
 *
 * @author igor
 */
public class NovoUsuarioController {
    private final NovoUsuario view;
    private final UsuarioDAO DAO;
    private final EntityManager em;

    public NovoUsuarioController(NovoUsuario view, EntityManager em) {
        this.view = view;
        this.em = em;
        DAO = new UsuarioDAO(em);
        
        try { view.setMaximum(true);
        } catch (PropertyVetoException ex) { Logger.getLogger(NovoAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cadastrarUsuario(){
        try {   
            String nome = view.getTxtNome().getText();
            String cpf = view.getTxtCpf().getText();
            String telefone = view.getTxtTelefone().getText();
            String senha = view.getTxtSenha().getText();
            String perguntaRecuperacao = view.getTxtPerguntaRecuperacao().getText();
            String respostaRecuperacao = view.getTxtRespostaRecuperacao().getText();
            String stringNivelAcesso = view.getCbxNivelAcesso().getSelectedItem().toString();

            NivelAcesso nivelAcesso = NivelAcesso.stringEnum(stringNivelAcesso);
            Usuario usuario = new Usuario(nome, cpf, telefone, senha, perguntaRecuperacao, respostaRecuperacao, nivelAcesso);
        
            if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || senha.isEmpty() || perguntaRecuperacao.isEmpty() || respostaRecuperacao.isEmpty()){
                System.out.println("SISTEMA: Existe campo vazio");
                JOptionPane.showMessageDialog(view, "Não deixe nenhum campo vazio.");
               
            }else{
                DAO.insert(usuario);
                System.out.println("SISTEMA: Usuario " + usuario.getNome() + " cadastrado");
                JOptionPane.showMessageDialog(view,"Usuário " + nome + " cadastrado com sucesso.");
            }
            
        } catch (SQLException ex) {
            System.out.println("SISTEMA: Erro ao cadastrar novo usuário");
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar novo usuário.");
            Logger.getLogger(NovoUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
