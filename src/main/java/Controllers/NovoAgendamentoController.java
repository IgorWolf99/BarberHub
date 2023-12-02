package Controllers;

import DAO.AgendamentoDAO;
import DAO.ClienteDAO;
import DAO.ServicoDAO;
import Helpers.NovoAgendamentoHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import models.Agendamento;
import models.Cliente;
import models.Servico;
import views.secundarias.NovoAgendamento;

/**
 *
 * @author igorw
 */
public class NovoAgendamentoController {
    private final NovoAgendamento view;
    private final EntityManager em;
    private final NovoAgendamentoHelper helper;
    private final AgendamentoDAO DAO;
    
    
    public NovoAgendamentoController(NovoAgendamento view, EntityManager em) {
        this.view = view;
        this.em = em;
        this.helper = new NovoAgendamentoHelper(view);
        DAO = new AgendamentoDAO(em);
        view.getLblResultadoOperacao().setVisible(false);
        
        try { view.setMaximum(true);
        } catch (PropertyVetoException ex) { Logger.getLogger(NovoAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    public void novoAgendamento() {
    try {
        // Buscar cliente
        String nomeCliente = view.getTxtCliente().getText();
        Cliente cliente = new ClienteDAO(em).selectByNome(nomeCliente);
        System.out.println("SISTEMA: Cliente encontrado: " + cliente.getNome());

        // Buscar Servico
        int id = helper.obterServico().getId();
        Servico servico = new ServicoDAO(em).selectById(id);
        
        // Valor  / Verificando campos vazios e texto no 'valor'
        String valorTexto = view.getTxtValor().getText().replace(",", ".");
        if (valorTexto.isEmpty()) {
            throw new IllegalArgumentException("O campo 'Valor' não pode estar vazio.");
        }
        if(!helper.isNumero(valorTexto)){
            throw  new IllegalArgumentException("Valor inválido.");
        }
        Double valor = Double.valueOf(valorTexto);
        
        // Data
        String data = view.getTxtData().getText();
        String hora = view.getTxtHora().getText();
        Date horarioAgendamento = helper.converteDataHora(data, hora);
        
        String observacao = view.getTxtObservacao().getText();
        Agendamento novoAgendamento = new Agendamento(cliente, servico, valor, horarioAgendamento, observacao);

        // Salvar no banco
        DAO.insert(novoAgendamento);
        
        helper.resultOperacao("Agendamento realizado", "Agendamento de " + cliente.getNome() + " realizado",true);
        view.getLblResultadoOperacao().setVisible(false);

    } catch (IllegalArgumentException ex) {
        helper.resultLabel(view.getLblResultadoOperacao(), ex.getMessage(),false, false);

    } catch (NoResultException ee) {
        helper.resultLabel(view.getLblResultadoOperacao(), "Usuário não encontrado no sistema",true, false);
        
    } catch (SQLException ex) {
        helper.resultLabel(view.getLblResultadoOperacao(), "ERRO INESPERADO",true, false);
        Logger.getLogger(NovoAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    /*  Realiza uma consulta JPQL para encontrar clientes cujos nomes comecem com a letra
      digitada no camplo cliente. A consulta é nao diferencia letras maiúsculas e minúsculas. */
    public void tabelaClientes(){
        String nomeDigitado = view.getTxtCliente().getText();

        // Pesquisa clientes pela letra digitada
        List<Cliente> clientes = new ClienteDAO(em).pesquisarClientesPorLetra(nomeDigitado);
        
        // Adicionar resultados na tabela
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableClientes().getModel();
        tableModel.setNumRows(0);

        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getNome(),
                cliente.getObservacao()
            });
        }
    }
    
    public void tabelaAgendamentos(){
        List<Agendamento> agendamentos = DAO.selectAllEmOrdem();
        DefaultTableModel tableModel = (DefaultTableModel) view.getTabelaAgendamentos().getModel();
        tableModel.setNumRows(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
        
        for(Agendamento agendamento: agendamentos){
            String dataFormatada = dateFormat.format(agendamento.getData());
            tableModel.addRow(new Object[]{
                    dataFormatada,
                    agendamento.getCliente().getNome(),
                    agendamento.getServico().getServico()          
            });   
        }
    }
    
    public void atualizaServicos(){
        List<Servico> servicos = new ServicoDAO(em).selectAll();
        DefaultComboBoxModel cbxModel = (DefaultComboBoxModel)view.getCbxServico().getModel();
        for (Servico servico : servicos){
            cbxModel.addElement(servico);
        }
    }
    
    public void atualizaValorServico(){
        Servico servico = helper.obterServico();
        helper.setarValor(servico.getValor());
    }
   
    // completa o nome do cliente no campo quando clicar na tabela
    public void preencherNomeCliente(){
        int nomeLista = view.getTableClientes().getSelectedRow();
        String nome = view.getTableClientes().getModel().getValueAt(nomeLista, 0).toString();
        view.getTxtCliente().setText(nome);
    }
   
}
