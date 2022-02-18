package br.ufpb.dcx.aps.atividades.curso_alunos;

public class Teste {
    public static void main(String[] args){
        Aluno a1 = new Aluno("2020111222");
        a1.setNome("João");
        Aluno a2 = new Aluno("2020111223");
        a2.setNome("Maria");
        Curso curso = new Curso(123,"SI / LCC");

        curso.matriculaAluno(a1);
        curso.matriculaAluno(a2);

        System.out.println("João = "+curso.getAluno("2020111222").getNome());
        System.out.println("Maria = "+curso.getAluno("2020111223").getNome());

        // Depois que matriculamos Maria no curso "SI / LCC",
        // quando recuperamos Maria de volta, a partir do curso,
        // seu curso deve ser "SI / LCC". Veja a seguir:
        Aluno maria = curso.getAluno("2020111223");
        System.out.println("O curso de Maria deve ser SI / LCC: "+maria.getCurso().getNome());
    }
}
