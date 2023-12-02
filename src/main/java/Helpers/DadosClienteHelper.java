package Helpers;

import jakarta.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import views.secundarias.DadosCliente;


public class DadosClienteHelper implements IHelper{
    private final DadosCliente view;
    
    public DadosClienteHelper(DadosCliente view) {
        this.view = view;
    }
    
    @Override
    public void limparCampos() {
       view.getTxtId().setText("");
       view.getTxtNome().setText("");
       view.getTxtTelefone().setText("");
       view.getTxtEndereco().setText("");
       view.getTxtNasc().setText("");
       view.getTxtCpf().setText("");
       view.getTxtObservacao().setText("");
    }
    
    public Date converterData(String data){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatoData.setLenient(false); // Não aceitar datas ou horas inválidas Ex "15/85"
            if(data.isEmpty()){
                System.out.println("SISTEMA: Campo Data vazio");
                throw new IllegalArgumentException("Campo 'Data' não pode estar vazio.");
            }
            Date dataConvertida = formatoData.parse(data);
            
            System.out.println("SISTEMA: Input Data: " + data);
            System.out.println("SISTEMA: Data Convertida: " + dataConvertida);
            
            return dataConvertida;
            
        } catch (ParseException ex) {
             System.out.println("SISTEMA: Data Inválida");
             throw new PersistenceException("Data inválida!\nVerifique se o campo 'Data' está preenchido corretamente");
        } 
    }
    
    public void verificaCamposVazios() {
    if (view.getTxtEndereco().getText().isEmpty() || view.getTxtNome().getText().isEmpty() || view.getTxtTelefone().getText().isEmpty() || view.getTxtCpf().getText().isEmpty()) {
        throw new IllegalArgumentException("Não deixe campos vazios.");
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
