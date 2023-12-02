package Controllers;

import Helpers.UsuarioLogado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import views.Menu;
import views.secundarias.NovoUsuario;

/**
 *
 * @author igorw
 */
public class MenuController {
    private final Menu view;

    public MenuController(Menu view) {
        this.view = view;
        preencherDadosUsuario();
    }
    
    private void preencherDadosUsuario(){
        UsuarioLogado usuario = UsuarioLogado.getInstance();
        view.getLblUsuarioLogado().setText(usuario.getNomeUsuario());
        
        view.getLblNivelAcesso().setText("Acesso: " + usuario.getNivelAcesso().getDescricao());
        
        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        view.getLblDataAtual().setText(dataAtual);
    }
    
    public void controleAcessoNovoUsuario(){
        if(!UsuarioLogado.getInstance().userIsAdm()){
            JOptionPane.showMessageDialog(view, "Somente o administrador pode acessar essa função.");
        }else{
            NovoUsuario novoUsuario = new NovoUsuario();
            novoUsuario.setVisible(true);
            view.getjDesktop().add(novoUsuario);
        }
    }
}
