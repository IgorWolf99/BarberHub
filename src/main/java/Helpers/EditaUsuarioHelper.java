package Helpers;

import javax.swing.JOptionPane;
import views.secundarias.EditaUsuario;

public class EditaUsuarioHelper implements IHelper{
    private final EditaUsuario view;

    public EditaUsuarioHelper(EditaUsuario view) {
        this.view = view;
    }

    @Override
    public void limparCampos() {
        view.getTxtNome().setText("");
        view.getTxtCpf().setText("");
        view.getTxtPerguntaRecuperacao().setText("");
        view.getTxtRespostaRecuperacao().setText("");
        view.getTxtSenha().setText("");
        view.getTxtTelefone().setText("");
    }
    
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }
    
    public void verTabelaUsuarios(){
        if(UsuarioLogado.getInstance().userIsAdm()){ 
            view.getjScrollPane1().setVisible(true);
            view.getTabelaUsuarios().setVisible(true);
        } else{
            view.getjScrollPane1().setVisible(false);
            view.getTabelaUsuarios().setVisible(false);
        }
    }
    
    public void habilitaDeletaUsuarioENivelAcesso(){
        if(UsuarioLogado.getInstance().userIsAdm()){
            view.getCbxNivelAcesso().setEnabled(true);
            view.getBtnExcluir().setEnabled(true);
        }else{
            view.getCbxNivelAcesso().setEnabled(false);
            view.getBtnExcluir().setEnabled(false);
        }
    }
    
}
