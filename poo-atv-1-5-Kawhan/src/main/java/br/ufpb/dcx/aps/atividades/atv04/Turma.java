package main.java.br.ufpb.dcx.aps.atividades.atv04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Turma {

    private int codigo;
    private Disciplina disciplina;
    private Professor professor;
    private Map<Aluno,Aluno> alunos = new HashMap<>();

    public Turma(int codigo, Disciplina disciplina) {
        this.codigo = codigo;
        this.disciplina = disciplina;

    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public List<Aluno> getAlunos() {
        List alunos2 = new ArrayList<>();
        for (Aluno aluno : alunos.values()) {
            alunos2.add(aluno);
        }
        return alunos2;
    }

    public void addAluno(Aluno aluno) {
        if(disciplina.getCurso().alunoMatriculado(aluno.getMatricula())) {
            alunos.put(aluno, aluno);
        } else {
            throw new RuntimeException("Aluno não está matriculado no curso:"+disciplina.getCurso().getNome());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return codigo == turma.codigo;
    }
}
