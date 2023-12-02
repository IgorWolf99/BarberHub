package models;

import Helpers.UsuarioLogado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "agendamentos")
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Servico servico;
    
    @ManyToOne
    private Usuario barbeiro;
    
    private Double valor;
    private Date horario;
    
    @Column(columnDefinition = "TEXT")  //Para o banco aceitar mais de 255 caracteres caso necessario
    private String observacao;
    
    private boolean finalizado;
    
    public Agendamento(){}
    
    public Agendamento(Cliente cliente, Servico servico, Double valor, Date horario, String observacao) {
    this.valor = valor;
    this.cliente = cliente;
    this.servico = servico;
    this.barbeiro = UsuarioLogado.getInstance().getBarbeiro();
    this.horario = horario;
    this.observacao = observacao;
    this.finalizado = false;
}
    public String stringFinalizado(){
        String result;
        if (finalizado){
            result = "OK";
        }else{
            result = "";
        }
        return result;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setData(Date horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Usuario barbeiro) {
        this.barbeiro = barbeiro;
    }  

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    
}
