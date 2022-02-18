package br.ufpb.dcx.aps.strategy.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    Campo campo;
    Formulario form;

    @BeforeEach
    void setUp() {
        campo = new Campo("campo");
        form = new Formulario("Teste");
    }

    @Test
    void testBasico() {
        assertEquals("campo",campo.getId());
        assertEquals("",campo.getNome());
        campo.setNome("Campo teste");
        assertEquals("Campo teste",campo.getNome());

        Campo c2 = new Campo("c2","Campo C2");
        assertEquals("c2",c2.getId());
        assertEquals("Campo C2",c2.getNome());
    }

    @Test
    void testCampoPreenchido() {
        //Quando um campo receber um valor, ele passa a ser "preenchido"
        // ou seja, o método "isPreenchido" só retorna true depois do campo
        // receber um valor
        assertFalse(campo.isPreenchido());
        campo.setValor("Valor Campo");
        assertTrue(campo.isPreenchido());

        assertEquals("Valor Campo",campo.getValor());
    }

    @Test
    void testObrigatorio() {
        // Por padrão, um campo não é obrigatório
        assertFalse(campo.isObrigatorio());

        campo.setObrigatorio(true);
        assertTrue(campo.isObrigatorio());

        Campo c = new Campo("c",true);
        assertTrue(c.isObrigatorio());

        c = new Campo("c2", true, "Campo C2");
        assertTrue(c.isObrigatorio());
        assertEquals("c2",c.getId());
        assertEquals("Campo C2", c.getNome());

        c = new Campo("c3", "Campo C3");
        assertFalse(c.isObrigatorio());
        assertEquals("c3",c.getId());
        assertEquals("Campo C3",c.getNome());
    }



    @Test
    void testValorCampo(){
        form.addItemFormulario(new Campo("nome","Digite seu nome:"));
        form.addItemFormulario(new Campo("email","Digite seu email:"));
        form.addItemFormulario(new Campo("telefone","Digite seu telefone:"));
        assertEquals("Digite seu nome:",form.getItemFormulario("nome").getNome());

        // Um campo só é preenchido quando é definido um valor
        assertFalse(form.getItemFormulario("nome").isPreenchido());
        form.getItemFormulario("nome").setValor("Joao da Silva");
        assertTrue(form.getItemFormulario("nome").isPreenchido());
    }

    @Test
    void testResultadoCampo() {
        Resultado r = campo.validar();
    }
}
