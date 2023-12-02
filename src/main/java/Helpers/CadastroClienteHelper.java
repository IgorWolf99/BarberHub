package Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import views.secundarias.CadastroCliente;


public class CadastroClienteHelper implements IHelper{
    private final CadastroCliente view;

    public CadastroClienteHelper(CadastroCliente view) {
        this.view = view;
    }
    
    @Override
    public void limparCampos() {
       view.getTxtNome().setText("");
       view.getTxtTelefone().setText("");
       view.getTxtEndereco().setText("");
       view.getTxtNasc().setText("");
       view.getTxtCpf().setText("");
       view.getTxtObservacao().setText("");
    }
    
    public Date converterStringDate(String horario){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
             return dateFormat.parse(horario);
            
        } catch (ParseException ex) {
            System.out.println("SISTEMA: Erro na conversao String > Data");
            throw new IllegalArgumentException("Data inválida!\nVerifique se o campo 'Data' está preenchido corretamente");
        }
    }
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }


}
