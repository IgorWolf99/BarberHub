package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Date nasc;
    private String telefone;
    private String endereco;
    private String cpf;
    
    @Column(columnDefinition = "TEXT")  //Para o banco aceitar mais de 255 caracteres caso necessario
    private String observacao;

    public Cliente(String nome, Date nasc, String telefone, String endereco, String cpf) {
        this.nome = nome;
        this.nasc = nasc;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
    }
    public Cliente(){};
    
    // Construtor sem Observação
    public Cliente(String nome, Date nasc, String endereco, String telefone, String cpf, String observacao) {
        this.nome = nome;
        this.nasc = nasc;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.observacao = observacao;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNasc() {
        return nasc;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

  

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
