package test.java.br.ufpb.dcx.aps.atividades.curso_alunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {


    private Curso curso;

    @BeforeEach
    void setUp() {
        curso = new Curso("123","Teste");
    }

    @Test
    void testCurso() {

        assertEquals("Teste",curso.getNome());
        curso.setNome("SI");
        assertEquals("SI", curso.getNome());

        curso.setDescricao("Descrição SI");
        assertEquals("Descrição SI",curso.getDescricao());

        assertEquals("123",curso.getCodigo());
    }

    @Test
    void testMatricula(){
        Aluno aluno = new Aluno("9811111");

        curso.matricular(aluno);

        assertEquals(aluno,curso.getAluno("9811111"));

        assertEquals(curso, aluno.getCurso());

        Exception e1 = assertThrows(RuntimeException.class, () ->
                       curso.matricular(new Aluno("9811111")) );
                assertEquals("Já existe um aluno com matrícula 9811111", e1.getMessage());

        Exception e2 = assertThrows(IllegalArgumentException.class, () ->
                       curso.matricular(null) );
                assertEquals("aluno == null!", e2.getMessage());


    }

    @Test
    void testMatriculaDesafio() {
        Aluno aluno = new Aluno("123");
        Curso si = new Curso("222","SI");
        si.matricular(aluno);

        Curso lcc = new Curso("111","LCC");
        Exception e = assertThrows(RuntimeException.class, () ->
                       aluno.setCurso(lcc) );
        assertEquals("curso inválido Curso{nome='LCC', codigoCurso='111'}! aluno '123' matriculado no curso Curso{nome='SI', codigoCurso='222'}", e.getMessage());
    }
}
