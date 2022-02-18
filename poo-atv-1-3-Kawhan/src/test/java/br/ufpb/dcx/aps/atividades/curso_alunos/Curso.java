package test.java.br.ufpb.dcx.aps.atividades.curso_alunos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Curso {

    private String nome;
    private String descricao;
    private String codigoCurso;
    private Map<String,Aluno> alunos = new HashMap<>();

    public Curso(String codigo, String nome) {
        this.codigoCurso = codigo;
        this.nome = nome;
    }

    public void matricular(Aluno aluno) throws RuntimeException {
        if(aluno == null) {
            throw new IllegalArgumentException("aluno == null!");
        }
        for (Aluno a : this.alunos.values()) {
            if(a.getMatricula().equals(aluno.getMatricula())) {
                throw new RuntimeException("Já existe um aluno com matrícula "+aluno.getMatricula());
            }
        }
        aluno.setCurso(this);
        this.alunos.put("matriculado",aluno);
    }

    public String getCodigo() {

        return this.codigoCurso;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getDescricao() {

        return this.descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public Aluno getAluno(String matricula) throws RuntimeException {
        for (Aluno aluno : this.alunos.values()) {
            if(aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        throw new RuntimeException("Está matrícula não está cadastrada.");
    }

    //Curso{nome='LCC', codigoCurso=111}!

    @Override
    public String toString() {
        return "Curso{nome='" + this.nome + "', codigoCurso='" + codigoCurso + "'}";
    }
}