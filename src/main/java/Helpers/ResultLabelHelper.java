package Helpers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;


public class ResultLabelHelper {
    public final Component view;

    public ResultLabelHelper(Component view) {
        this.view = view;
    }
    
    public static void exibirResultadoOperacao(JLabel label, String mensagem, boolean resultado) {
        label.setVisible(true);
        label.setText(mensagem);
        label.setForeground(resultado ? new Color(0, 128, 0) : new Color(190, 0, 0));
    }
    
}
