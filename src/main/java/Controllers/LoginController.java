package Controllers;

import DAO.UsuarioDAO;
import Helpers.LoginHelper;
import Helpers.UsuarioLogado;
import jakarta.persistence.EntityManager;
import java.util.List;
import models.Usuario;
import views.Login;
import views.Menu;

/**
 *
 * @author igor
 */
public class LoginController {
    private final Login view;
    private final EntityManager em;
    private final UsuarioDAO DAO;
    private final LoginHelper helper;

    public LoginController(Login view, EntityManager em) {
        this.view = view;
        this.em = em;
        DAO = new UsuarioDAO(em);  
        helper = new LoginHelper(view);
        
        preencherCampoUsuario();
    }
    
    public void logar(){
        String nome = view.getCbxUsuario().getSelectedItem().toString();
        String senha = view.getTxtSenha().getText();
        
        Usuario usuario = DAO.usuarioNomeSenha(nome, senha);
        
        if(usuario != null){
            System.out.println("SISTEMA: Usuário autenticado");
            
            capturarUsuarioLogado(usuario);
            
            view.dispose();
            Menu menu = new Menu();
            menu.setVisible(true);
            
        }else{
            helper.resultOperacao("Usuário ou Senha Incorretos.", "Usuário ou Senha Incorretos.", true);
        }
    }
    
    public void preencherCampoUsuario(){
        List<Usuario> usuarios = DAO.selectAll();   
        for(Usuario usuario: usuarios){
            view.getCbxUsuario().addItem(usuario.toString());
        }
    }
    
    public void verSenha(){
       if(view.getBtnMostrarSenha().isSelected()){
           view.getTxtSenha().setEchoChar((char) 0);
       }else{
           view.getTxtSenha().setEchoChar('\u2022');
       }
    }
       
    //Login pelo esqueceuSenha
    public void entrarEsqueceuSenha(){
        String nome = view.getCbxUsuario().getSelectedItem().toString();
        String resposta = view.getTxtResposta().getText();
        
        Usuario usuario = DAO.respostaSeguranca(nome,resposta);
        if(usuario != null){
            System.out.println("SISTEMA: Entrada por 'Esqueceu a senha'");
            System.out.println("SISTEMA: Usuário autenticado");

            capturarUsuarioLogado(usuario);
            
            Menu menu = new Menu();
            menu.setVisible(true);
            view.dispose();  
            view.getDialogEsqueceuSenha().dispose();
            
           }else{
            helper.resultOperacao("Resposta Incorreta.", "Resposta Incorreta.", true);
        }
    }
    
    // Metodo para buscar a pergunta de recuperação do usuario logado
    public String perguntaUsuario(){
        Usuario user = DAO.selectByNome(view.getCbxUsuario().getSelectedItem().toString());
        return user.getPerguntaSeguranca();
    }
    
    public void capturarUsuarioLogado(Usuario usuario){
        UsuarioLogado usuarioLogado= UsuarioLogado.getInstance();
        usuarioLogado.setNomeUsuario(usuario.getNome());
        usuarioLogado.setBarbeiro(usuario);
        usuarioLogado.setIdBarbeiro(usuario.getId());
        usuarioLogado.setNivelAcesso(usuario.getNivelAcesso());
    }
    
}
