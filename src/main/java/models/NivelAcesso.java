package models;

public enum NivelAcesso {
    USUARIO("Usuário"),
    ADMINISTRADOR("Administrador");
    
    private final String descricao;
    
     NivelAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    // Converte uma string para o valor respectivo do enum
    public static NivelAcesso stringEnum(String nivelAcessoString) {
        for (NivelAcesso nivel : NivelAcesso.values()) {
            if (nivel.descricao.equalsIgnoreCase(nivelAcessoString)) {
                return nivel;
            }
        }
        return null;
    }
}
