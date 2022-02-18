package test.java.br.ufpb.dcx.aps.atividades.atv04;

import main.java.br.ufpb.dcx.aps.atividades.atv04.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AtividadeTest {


    Curso curso, cursoComAlunos;

    @BeforeEach
    void setUp() {
        curso = new Curso("LCC");
        cursoComAlunos = new Curso("SI");
        cursoComAlunos.matricular(new Aluno("1","Joao"));
        cursoComAlunos.matricular(new Aluno("2","Maria"));
        cursoComAlunos.matricular(new Aluno("3","Jose"));
        cursoComAlunos.matricular(new Aluno("4","Carlos"));
    }


    @Test
    void testAluno() {

        Aluno joao = new Aluno("1","joao");
        assertEquals("joao", joao.getNome());
        assertEquals("1", joao.getMatricula());
        joao.setNome("Joao Feijao");
        assertEquals("Joao Feijao",joao.getNome());

        // dois alunos são iguais se tiverem a mesma matrícula
        assertEquals(new Aluno("333","Joao"),new Aluno("333","Maria"));

    }


    @Test
    void testProfessor() {

        Professor rodrigo = new Professor("123","Rodrigo Rebouças");
        assertEquals("123",rodrigo.getSiape());
        assertEquals("Rodrigo Rebouças",rodrigo.getNome());

        Professor teste = new Professor("333");
        assertEquals("333",teste.getSiape());
        assertNotNull(teste.getNome());
        assertEquals("",teste.getNome());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new Professor(null));
        assertEquals("siape invalido:null", e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () ->
                new Professor(""));
        assertEquals("siape invalido:", e.getMessage());
    }


    @Test
    void testCurso() {
        assertEquals("LCC",curso.getNome());
        curso.setNome("Lic. Comp.");
        assertEquals("Lic. Comp.", curso.getNome());
        Curso nada = new Curso();
        assertEquals("",nada.getNome());
    }

    @Test
    void testMatriculaAlunosCurso() {
        assertEquals(0,curso.getAlunosMatriculados().size());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                curso.matricular(null));
        assertEquals("aluno inválido:null", e.getMessage());

        curso.matricular(new Aluno("123","Joao"));
        e = assertThrows(RuntimeException.class, () ->
                curso.matricular(new Aluno("123","Maria")));
        assertEquals("aluno já matriculado:123", e.getMessage());

    }

    @Test
    void testDisciplina() {
        Exception e = assertThrows(RuntimeException.class, () ->
                curso.getDisciplina("001"));
        assertEquals("Não existe disciplina com código:001", e.getMessage());

        Disciplina aps = curso.criarDisciplina("001","APS");
        assertEquals(aps, curso.getDisciplina("001"));
        assertEquals(curso,aps.getCurso());
    }

    @Test
    void testTurma() {
        Disciplina aps = curso.criarDisciplina("001","APS");

        // Códigos de turma negativos ou zero são inválidos
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                aps.getTurma(-1));
        assertEquals("código inválido:-1", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () ->
                aps.getTurma(0));
        assertEquals("código inválido:0", e.getMessage());

        e = assertThrows(RuntimeException.class, () ->
                aps.getTurma(1));
        assertEquals("Não existe turma com código:1", e.getMessage());

        e = assertThrows(RuntimeException.class, () ->
                aps.getTurma(23423423));
        assertEquals("Não existe turma com código:23423423", e.getMessage());

        assertEquals(0,aps.getTurmas().size());


        //Criando turmas
        Turma t1 = aps.criarTurma();
        assertNotNull(t1);
        assertEquals(1,t1.getCodigo());
        assertEquals(1,aps.getTurmas().size());
        Turma t2 = aps.criarTurma();
        assertNotNull(t2);
        assertEquals(2,t2.getCodigo());
        assertEquals(2,aps.getTurmas().size());

        //Definindo Professor
        Professor rodrigo = new Professor("123","Rodrigo Rebouças");
        t1.setProfessor(rodrigo);
        assertEquals(rodrigo,t1.getProfessor());

    }







    @Test
    void testMatricularAlunosTurma() {
        Disciplina aps = cursoComAlunos.criarDisciplina("111","APS");
        Turma turma1 = aps.criarTurma();

        turma1.addAluno(new Aluno("1","Joao"));

        // O aluno precisa estar matriculado no curso para ser adicionado numa turma
        Exception e = assertThrows(RuntimeException.class, () ->
                turma1.addAluno(new Aluno("9","Zoroastro")));
        assertEquals("Aluno não está matriculado no curso:SI", e.getMessage());


    }

}
