package br.ufpb.dcx.aps.atividades.curso_alunos;

public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;


    public void setCurso(Curso cursoo){
        this.curso = cursoo;
    }

    public Aluno(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
