package Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.Servico;
import views.secundarias.NovoAgendamento;

/**
 *
 * @author igor
 */
public class NovoAgendamentoHelper implements IHelper {
    private final NovoAgendamento view;

    public NovoAgendamentoHelper(NovoAgendamento view) {
        this.view = view;
    }
    
    @Override
    public void limparCampos() {
        view.getTxtCliente().setText("");
        view.getCbxServico().setSelectedIndex(0);
        view.getTxtData().setText("");
        view.getTxtHora().setText("");
        view.getTxtValor().setText("");
        view.getTxtObservacao().setText("");
    }
    
    public Date converteDataHora(String data, String hora){
        System.out.println("SISTEMA: Data Input: " + data);
        System.out.println("SISTEMA: Hora Input: " + hora);
        
        if(data.isEmpty()){
            System.out.println("SISTEMA: Campo Data vazio");
            throw new IllegalArgumentException("Campo 'Data' não pode estar vazio.");
        }else if(hora.isEmpty()){
            System.out.println("SISTEMA: Campo Hora vazio");
            throw new IllegalArgumentException("Campo 'Hora' não pode estar vazio.");
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
            dateFormat.setLenient(false); // Não aceitar datas ou horas inválidas Ex "15/85"

            String dataHora = data + " " + hora;
            Date dataHoraParseada = dateFormat.parse(dataHora);
            System.out.println("SISTEMA: Data Parse: " + dataHoraParseada);
            
            return dataHoraParseada;
        } catch (ParseException ex) {
            System.out.println("SISTEMA: Data ou horário Inválido");
            throw new IllegalArgumentException("Data ou horário inválidos");
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
    
    public void resultOperacao(String mensagemLog, String mensagemTela, boolean limparCampos){
        System.out.println("SISTEMA: "+ mensagemLog);
        JOptionPane.showMessageDialog(view, mensagemTela);
        if(limparCampos){
            limparCampos();
        }
    }
    
    public void resultLabel(JLabel label, String mensagem,boolean limparCampos, boolean resultado){
        ResultLabelHelper.exibirResultadoOperacao(label, mensagem, resultado);
        if(limparCampos){
            limparCampos();
        }
    }
       
    public Servico obterServico(){
        return (Servico) view.getCbxServico().getSelectedItem();
    }
    
    public void setarValor(Double valor){
        view.getTxtValor().setText(valor.toString().replace(".",","));
    }
}