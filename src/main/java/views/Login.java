package views;

import Controllers.LoginController;
import DAO.JPAUtil;
import Helpers.PrimeiroLogin;
import jakarta.persistence.EntityManager;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author igor
 */
public class Login extends javax.swing.JFrame {
    private final LoginController controller;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        EntityManager em = new JPAUtil().getEntityManager();
        
        new PrimeiroLogin(em).usuarioPrimeiroLogin();
        
        controller = new LoginController(this, em);
        
        //Dimensiona o frame principal para o centro da tela
        this.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (this.getWidth() / 2)), 
                ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.getHeight() / 2)));
        
        //Dimensiona o frame de esqueceu a senha para o centro
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        dialogEsqueceuSenha.setBounds((screenSize.width-460)/2,(screenSize.height-380)/2, 460, 380);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogEsqueceuSenha = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPergunta = new javax.swing.JTextField();
        txtResposta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnEntrarEsqueceuSenha = new javax.swing.JButton();
        panelLogo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        cbxUsuario = new javax.swing.JComboBox<>();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnMostrarSenha = new javax.swing.JToggleButton();
        btnEsqueceuSenha = new javax.swing.JButton();

        dialogEsqueceuSenha.setMinimumSize(new java.awt.Dimension(460, 380));
        dialogEsqueceuSenha.setModal(true);

        jPanel1.setMaximumSize(new java.awt.Dimension(460, 380));
        jPanel1.setMinimumSize(new java.awt.Dimension(460, 380));
        jPanel1.setPreferredSize(new java.awt.Dimension(460, 380));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/logoIcone.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Esqueceu a senha?");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 38, 218, 35));

        jLabel2.setText("Pergunta de segurança");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 122, -1, -1));

        txtPergunta.setEditable(false);
        jPanel1.add(txtPergunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 146, 281, -1));
        jPanel1.add(txtResposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 213, 281, -1));

        jLabel6.setText("Resposta");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 191, -1, -1));

        btnEntrarEsqueceuSenha.setText("Entrar");
        btnEntrarEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarEsqueceuSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(btnEntrarEsqueceuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 253, 85, 32));

        javax.swing.GroupLayout dialogEsqueceuSenhaLayout = new javax.swing.GroupLayout(dialogEsqueceuSenha.getContentPane());
        dialogEsqueceuSenha.getContentPane().setLayout(dialogEsqueceuSenhaLayout);
        dialogEsqueceuSenhaLayout.setHorizontalGroup(
            dialogEsqueceuSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogEsqueceuSenhaLayout.setVerticalGroup(
            dialogEsqueceuSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelLogo.setBackground(new java.awt.Color(60, 60, 60));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Logo.jpg"))); // NOI18N

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        lblLogin.setBackground(new java.awt.Color(60, 60, 60));
        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblLogin.setText("Login");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsuario.setText("Usuário");

        lblSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSenha.setText("Senha");

        cbxUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxUsuario.setFocusable(false);

        txtSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnEntrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/perfil-de-usuario.png"))); // NOI18N

        btnMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/olho.png"))); // NOI18N
        btnMostrarSenha.setToolTipText("");
        btnMostrarSenha.setBorder(null);
        btnMostrarSenha.setBorderPainted(false);
        btnMostrarSenha.setContentAreaFilled(false);
        btnMostrarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarSenhaActionPerformed(evt);
            }
        });

        btnEsqueceuSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEsqueceuSenha.setText("Esqueceu a senha?");
        btnEsqueceuSenha.setActionCommand("");
        btnEsqueceuSenha.setBorder(null);
        btnEsqueceuSenha.setContentAreaFilled(false);
        btnEsqueceuSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueceuSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblLogin)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                            .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblSenha)
                                .addComponent(lblUsuario)
                                .addComponent(cbxUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMostrarSenha))))
                    .addComponent(btnEsqueceuSenha))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(lblLogin)
                .addGap(29, 29, 29)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMostrarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEsqueceuSenha)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        controller.logar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarSenhaActionPerformed
        controller.verSenha();
    }//GEN-LAST:event_btnMostrarSenhaActionPerformed

    private void btnEntrarEsqueceuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarEsqueceuSenhaActionPerformed
        controller.entrarEsqueceuSenha();

    }//GEN-LAST:event_btnEntrarEsqueceuSenhaActionPerformed

    private void btnEsqueceuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsqueceuSenhaActionPerformed
        txtPergunta.setText(controller.perguntaUsuario());
        
        dialogEsqueceuSenha.setVisible(true);
    }//GEN-LAST:event_btnEsqueceuSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    public JPanel getPanelLogo() {
        return panelLogo;
    }

    public void setPanelLogo(JPanel panelLogo) {
        this.panelLogo = panelLogo;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(JPasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(JButton btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    public JComboBox<String> getCbxUsuario() {
        return cbxUsuario;
    }

    public void setCbxUsuario(JComboBox<String> cbxUsuario) {
        this.cbxUsuario = cbxUsuario;
    }

    public JToggleButton getBtnMostrarSenha() {
        return btnMostrarSenha;
    }

    public void setBtnMostrarSenha(JToggleButton btnMostrarSenha) {
        this.btnMostrarSenha = btnMostrarSenha;
    }

    public JDialog getDialogEsqueceuSenha() {
        return dialogEsqueceuSenha;
    }

    public void setDialogEsqueceuSenha(JDialog dialogEsqueceuSenha) {
        this.dialogEsqueceuSenha = dialogEsqueceuSenha;
    }

    public JTextField getTxtPergunta() {
        return txtPergunta;
    }

    public void setTxtPergunta(JTextField txtPergunta) {
        this.txtPergunta = txtPergunta;
    }

    public JTextField getTxtResposta() {
        return txtResposta;
    }

    public void setTxtResposta(JTextField txtResposta) {
        this.txtResposta = txtResposta;
    }

    public JButton getBtnEsqueceuSenha() {
        return btnEsqueceuSenha;
    }

    public void setBtnEsqueceuSenha(JButton btnEsqueceuSenha) {
        this.btnEsqueceuSenha = btnEsqueceuSenha;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnEntrarEsqueceuSenha;
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JToggleButton btnMostrarSenha;
    private javax.swing.JComboBox<String> cbxUsuario;
    private javax.swing.JDialog dialogEsqueceuSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JTextField txtPergunta;
    private javax.swing.JTextField txtResposta;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
