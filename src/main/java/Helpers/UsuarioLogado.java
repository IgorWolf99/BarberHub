package Helpers;

import models.NivelAcesso;
import models.Usuario;

/**
 *
 * @author igorw
 */
public class UsuarioLogado {
    
    // Variável estática que conterá a instancia da classe
    private static UsuarioLogado instance;
    private String nomeUsuario;
    private Usuario barbeiro;
    private Integer idBarbeiro;
    private NivelAcesso nivelAcesso;
   
    private UsuarioLogado() {}

    // Método público estatico de acesso único ao objeto!
    public static UsuarioLogado getInstance() {
        if (instance == null)
            instance = new UsuarioLogado();
        return instance;
    }
    
    // Método para verificar se o usuario logado é um administrador
    public boolean userIsAdm(){
        if("Administrador".equals(this.getNivelAcesso().getDescricao())){
            return true;
        }else{
            return false;
        }
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Usuario getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Usuario barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Integer getIdBarbeiro() {
        return idBarbeiro;
    }

    public void setIdBarbeiro(Integer idBarbeiro) {
        this.idBarbeiro = idBarbeiro;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
   
}
