package main.java.br.ufpb.dcx.aps.atividades.atv04;

public class Professor {

    private String siape;
    private String nome;

    public Professor(String siape, String nome) {
        this.siape = siape;
        this.nome = nome;
    }

    public Professor(String siape) {
        if(siape == null || siape.equals("")) {
            throw new IllegalArgumentException("siape invalido:"+siape);
        }
        this.nome = "";
        this.siape = siape;
    }

    public String getSiape() {
        return this.siape;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
    
}
