package Helpers;

import javax.swing.JOptionPane;
import views.Login;

/**
 *
 * @author igor
 */
public class LoginHelper implements IHelper{
    private final Login view;

    public LoginHelper(Login view) {
        this.view = view;
    }
    
    @Override
    public void limparCampos() {
        view.getTxtSenha().setText("");
        view.getTxtResposta().setText("");
    }
    
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }
}
