package test.java.br.ufpb.dcx.aps.atividades.curso_alunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {


    Aluno aluno;
    @BeforeEach
    void setUp() {
        aluno = new Aluno("981222");
    }

    @Test
    void testNome() {
        assertEquals("",aluno.getNome());

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                       aluno.setNome(null) );
        assertEquals("nome == null!", e.getMessage());
    }

    @Test
    void testToString() {
        Aluno aluno = new Aluno("123");
        assertEquals("Aluno{nome='', matricula='123', curso=null}",aluno.toString());

        aluno.setNome("Maria");
        assertEquals("Aluno{nome='Maria', matricula='123', curso=null}",aluno.toString());

        Curso curso = new Curso("111","Curso Teste");
        curso.matricular(aluno);
        assertEquals("Aluno{nome='Maria', matricula='123', curso=Curso{nome='Curso Teste', codigoCurso='111'}}",aluno.toString());
    }
}
