package views.secundarias;

import Controllers.NovoUsuarioController;
import DAO.JPAUtil;
import jakarta.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
    
/**
 *
 * @author igor
 */
public class NovoUsuario extends javax.swing.JInternalFrame {
    private final NovoUsuarioController controller;
    
    /**
     * Creates new form NovoUsuario
     */
    public NovoUsuario() {
        initComponents();
        EntityManager em = new JPAUtil().getEntityManager();
        controller = new NovoUsuarioController(this,em);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtPerguntaRecuperacao = new javax.swing.JTextField();
        txtRespostaRecuperacao = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblEndereco1 = new javax.swing.JLabel();
        lblEndereco2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxNivelAcesso = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 120, 30));
        jPanel1.add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 120, 30));
        jPanel1.add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 120, 30));
        jPanel1.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 120, 30));
        jPanel1.add(txtPerguntaRecuperacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 200, 30));
        jPanel1.add(txtRespostaRecuperacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 103, 30));

        lblTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefone.setText("Telefone:");
        jPanel1.add(lblTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome.setText("Nome:");
        jPanel1.add(lblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        lblCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCpf.setText("Cpf:");
        jPanel1.add(lblCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        lblSenha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSenha.setText("Senha:");
        jPanel1.add(lblSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        btnAdicionar.setText("Cadastrar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 140, 41));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setText("Cadastrar novo usuário");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblEndereco1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEndereco1.setText("Pergunta de recuperação:");
        jPanel1.add(lblEndereco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        lblEndereco2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEndereco2.setText("Resposta:");
        jPanel1.add(lblEndereco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/adicionarbarbeiro.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        cbxNivelAcesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário", "Administrador" }));
        jPanel1.add(cbxNivelAcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 132, 160, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        controller.cadastrarUsuario();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    public JTextField getTxtCpf() {
        return txtCpf;
    }

    public void setTxtCpf(JTextField txtCpf) {
        this.txtCpf = txtCpf;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtPerguntaRecuperacao() {
        return txtPerguntaRecuperacao;
    }

    public void setTxtPerguntaRecuperacao(JTextField txtPerguntaRecuperacao) {
        this.txtPerguntaRecuperacao = txtPerguntaRecuperacao;
    }

    public JTextField getTxtRespostaRecuperacao() {
        return txtRespostaRecuperacao;
    }

    public void setTxtRespostaRecuperacao(JTextField txtRespostaRecuperacao) {
        this.txtRespostaRecuperacao = txtRespostaRecuperacao;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public void setTxtSenha(JPasswordField txtSenha) {
        this.txtSenha = txtSenha;
    }

    public JTextField getTxtTelefone() {
        return txtTelefone;
    }

    public void setTxtTelefone(JTextField txtTelefone) {
        this.txtTelefone = txtTelefone;
    }

    public JComboBox<String> getCbxNivelAcesso() {
        return cbxNivelAcesso;
    }

    public void setCbxNivelAcesso(JComboBox<String> cbxNivelAcesso) {
        this.cbxNivelAcesso = cbxNivelAcesso;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JComboBox<String> cbxNivelAcesso;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEndereco1;
    private javax.swing.JLabel lblEndereco2;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPerguntaRecuperacao;
    private javax.swing.JTextField txtRespostaRecuperacao;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}