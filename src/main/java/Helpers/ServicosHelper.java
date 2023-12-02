package Helpers;

import javax.swing.JOptionPane;
import models.Servico;
import views.secundarias.Servicos;

/**
 *
 * @author igor
 */
public class ServicosHelper implements IHelper {
    private final Servicos view;

    public ServicosHelper(Servicos view) {
        this.view = view;
    }
    
    @Override
    public void limparCampos() {
        view.getTxtNomeServico().setText("");
        view.getTxtValor().setText("");
        view.getTxtValorNovoServico().setText("");
        view.getCbxServico().setSelectedIndex(0);
    }
    
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }
    
    public boolean isNumero(String valor){
        try{
            Double.valueOf(valor);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
    
    public Servico obterServico(){
        return (Servico) view.getCbxServico().getSelectedItem();
    }
    
    public void setarValor(Double valor){
        view.getTxtValor().setText(valor.toString().replace(".", ","));
    }
}
