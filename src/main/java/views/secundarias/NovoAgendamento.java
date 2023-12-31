package views.secundarias;

import Controllers.NovoAgendamentoController;
import DAO.JPAUtil;
import jakarta.persistence.EntityManager;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author igorw
 */
public class NovoAgendamento extends javax.swing.JInternalFrame {

    private final NovoAgendamentoController controller;
    
    public NovoAgendamento() {
        initComponents();
        EntityManager em = new JPAUtil().getEntityManager();
        
        controller = new NovoAgendamentoController(this,em);
        controller.atualizaServicos();
        card.add(jScrollPaneClientes,"TabelaClientes");
        card.add(jScrollPaneAgendamentos,"TabelaAgendamentos");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        cbxServico = new javax.swing.JComboBox<>();
        lblServico = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblData1 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        lblhora = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        txtObsServico = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        lblObservacao = new javax.swing.JLabel();
        btnAgendar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        btnAgendamentos = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        lblResultadoOperacao = new javax.swing.JLabel();
        card = new javax.swing.JPanel();
        jScrollPaneAgendamentos = new javax.swing.JScrollPane();
        TabelaAgendamentos = new javax.swing.JTable();
        jScrollPaneClientes = new javax.swing.JScrollPane();
        TabelaClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCliente.setText("Cliente:");
        jPanel1.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        cbxServico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicoItemStateChanged(evt);
            }
        });
        jPanel1.add(cbxServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 140, -1));

        lblServico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblServico.setText("Serviço:");
        jPanel1.add(lblServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        lblValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblValor.setText("Valor: R$");
        jPanel1.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 140, -1));

        lblData1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblData1.setText("Data:");
        jPanel1.add(lblData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txtData.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });
        jPanel1.add(txtData, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 60, -1));

        lblhora.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblhora.setText("Hora:");
        jPanel1.add(lblhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        jPanel1.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 60, -1));

        txtObservacao.setColumns(20);
        txtObservacao.setLineWrap(true);
        txtObservacao.setRows(5);
        txtObservacao.setWrapStyleWord(true);
        txtObsServico.setViewportView(txtObservacao);

        jPanel1.add(txtObsServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 220, 100));

        lblObservacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObservacao.setText("Observação do serviço:");
        jPanel1.add(lblObservacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        btnAgendar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAgendar.setText("Agendar");
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 330, 40));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        Titulo.setText("Agendamento");
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnAgendamentos.setText("Agendamentos");
        btnAgendamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendamentosActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgendamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 110, -1));
        jPanel1.add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 60, -1));

        lblResultadoOperacao.setBackground(new java.awt.Color(190, 0, 0));
        lblResultadoOperacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblResultadoOperacao.setText("\"Resultado Operação\"");
        jPanel1.add(lblResultadoOperacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 320, -1));

        card.setLayout(new java.awt.CardLayout());

        TabelaAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Horário", "Cliente", "Serviço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaAgendamentos.setToolTipText("");
        jScrollPaneAgendamentos.setViewportView(TabelaAgendamentos);

        card.add(jScrollPaneAgendamentos, "card3");

        TabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Observação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaClientesMouseClicked(evt);
            }
        });
        jScrollPaneClientes.setViewportView(TabelaClientes);

        card.add(jScrollPaneClientes, "card2");

        jPanel1.add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 410, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/novoAgendamento.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
        controller.novoAgendamento();
    }//GEN-LAST:event_btnAgendarActionPerformed

    private void btnAgendamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendamentosActionPerformed
        controller.tabelaAgendamentos();
        CardLayout cardLayout = (CardLayout) card.getLayout();
        cardLayout.show(card, "TabelaAgendamentos");;
    }//GEN-LAST:event_btnAgendamentosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
         CardLayout cardLayout = (CardLayout) card.getLayout();
         cardLayout.show(card, "TabelaClientes");
        
    }//GEN-LAST:event_btnClientesActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        controller.tabelaClientes();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void TabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaClientesMouseClicked
        controller.preencherNomeCliente();
    }//GEN-LAST:event_TabelaClientesMouseClicked

    private void cbxServicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicoItemStateChanged
        controller.atualizaValorServico();
    }//GEN-LAST:event_cbxServicoItemStateChanged

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
       
    }//GEN-LAST:event_txtDataActionPerformed

    public JButton getBtnAgendamentos() {
        return btnAgendamentos;
    }

    public void setBtnAgendamentos(JButton btnAgendamentos) {
        this.btnAgendamentos = btnAgendamentos;
    }

    public JButton getBtnAgendar() {
        return btnAgendar;
    }

    public void setBtnAgendar(JButton btnAgendar) {
        this.btnAgendar = btnAgendar;
    }

    public JButton getBtnClientes() {
        return btnClientes;
    }

    public void setBtnClientes(JButton btnClientes) {
        this.btnClientes = btnClientes;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public JTable getTableClientes() {
        return TabelaClientes;
    }

    public void setTableClientes(JTable TableClientes) {
        this.TabelaClientes = TableClientes;
    }

    public void setTxtCliente(JTextField txtCliente) {
        this.txtCliente = txtCliente;
    }

    public JTextField getTxtData() {
        return txtData;
    }

    public void setTxtData(JTextField txtData) {
        this.txtData = txtData;
    }

    public JTextField getTxtHora() {
        return txtHora;
    }

    public void setTxtHora(JTextField txtHora) {
        this.txtHora = txtHora;
    }

    public JScrollPane getTxtObsServico() {
        return txtObsServico;
    }

    public void setTxtObsServico(JScrollPane txtObsServico) {
        this.txtObsServico = txtObsServico;
    }

    public JTextArea getTxtObservacao() {
        return txtObservacao;
    }

    public void setTxtObservacao(JTextArea txtObservacao) {
        this.txtObservacao = txtObservacao;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void setTxtValor(JTextField txtValor) {
        this.txtValor = txtValor;
    }

    public JLabel getLblResultadoOperacao() {
        return lblResultadoOperacao;
    }

    public void setLblResultadoOperacao(JLabel lblResultadoOperacao) {
        this.lblResultadoOperacao = lblResultadoOperacao;
    }

    public JComboBox<String> getCbxServico() {
        return cbxServico;
    }

    public void setCbxServico(JComboBox<String> cbxServico) {
        this.cbxServico = cbxServico;
    }

    public JTable getTabelaAgendamentos() {
        return TabelaAgendamentos;
    }

    public void setTabelaAgendamentos(JTable TabelaAgendamentos) {
        this.TabelaAgendamentos = TabelaAgendamentos;
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaAgendamentos;
    private javax.swing.JTable TabelaClientes;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton btnAgendamentos;
    private javax.swing.JButton btnAgendar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JPanel card;
    private javax.swing.JComboBox<String> cbxServico;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneAgendamentos;
    private javax.swing.JScrollPane jScrollPaneClientes;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblResultadoOperacao;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblhora;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtHora;
    private javax.swing.JScrollPane txtObsServico;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
