package test.java.br.ufpb.dcx.aps.atividades.curso_alunos;

public class Aluno {

    private String nome;
    private String matricula;
    private Curso curso;

    public Aluno(String matricula) {
        this.matricula = matricula;
        this.nome = "";
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if(nome == null) {
            throw new IllegalArgumentException("nome == null!");
        }
        this.nome = nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso novoCurso) throws RuntimeException {
        if(this.getCurso() != null) {
            throw new RuntimeException("curso inválido "+novoCurso+"! aluno '"+this.matricula+"' matriculado no curso "+this.curso);
        }
        this.curso = novoCurso;
    }

    @Override
    public String toString() {
        return "Aluno{nome='"+this.nome+"', matricula='"+this.matricula+"', curso="+this.curso+"}";
    }


}