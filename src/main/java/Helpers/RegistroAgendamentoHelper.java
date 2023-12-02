package Helpers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.Agendamento;
import views.secundarias.RegistroAgendamento;

/**
 *
 * @author igor
 */
public class RegistroAgendamentoHelper implements IHelper{
    private final RegistroAgendamento view;

    public RegistroAgendamentoHelper(RegistroAgendamento view) {
        this.view = view;
    }
    
    public Date converterStringDate(String horario) throws SQLException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
        
        try {
             return dateFormat.parse(horario);
            
        } catch (ParseException ex) {
            System.out.println("Erro na conversao String > Data");
            throw new IllegalArgumentException("Data inválida!\nVerifique se o campo 'Data' está preenchido corretamente.");
        }
    }
    
    public void lblAgendamentoFinalizado(Agendamento agendamento){
        view.getLblFinalizado().setVisible(true);
        view.getCbFinalizado().setVisible(true);
        if(agendamento.isFinalizado()){
                ImageIcon iconOK = new ImageIcon(getClass().getResource("/icones/checkIcon.png"));
                view.getCbFinalizado().setIcon(iconOK);
            } else {
                ImageIcon iconNotOK = new ImageIcon(getClass().getResource("/icones/notCheckIcon.png"));
                view.getCbFinalizado().setIcon(iconNotOK);
            }
    }
    
    @Override
    public void limparCampos() {
        view.getTxtId().setText("");
        view.getTxtCliente().setText("");
        view.getCbxServico().setSelectedIndex(0);
        view.getTxtHorario().setText("");
        view.getTxtObservacao().setText("");
        view.getTxtValor().setText("");
        view.getLblFinalizado().setVisible(false);
        view.getCbFinalizado().setVisible(false);
    }
    
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }
}
