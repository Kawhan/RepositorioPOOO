package br.ufpb.dcx.aps.atividades.atv05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormularioTest {


    Formulario form;

    @BeforeEach
    void setUp() {

        form = new Formulario();

    }

    @Test
    void testBasico() {
        // O título deve ser inicializado com ""
        assertEquals("",form.getTitulo());

        // O formulário deve estar vazio (sem nenhum campo existente)
        assertTrue(form.getCampos().isEmpty());

        form.setTitulo("Titulo");
        assertEquals("Titulo", form.getTitulo());
    }

    @Test
    void testCampos() {
        form.addCampo(new Campo("nome","Digite seu nome:"));
        form.addCampo(new Campo("email","Digite seu email:"));
        form.addCampo(new Campo("telefone","Digite seu telefone:"));
        assertEquals(3,form.getCampos().size());

        //Um formulário não pode ter mais de um campo com o mesmo id
        Exception e = assertThrows(Exception.class, () ->
                form.addCampo(new Campo("nome","Digite seu nome:")));
                assertEquals("Já existe um campo com este id:'nome'", e.getMessage());
    }

    @Test
    void testValorCampo(){
        form.addCampo(new Campo("nome","Digite seu nome:"));
        form.addCampo(new Campo("email","Digite seu email:"));
        form.addCampo(new Campo("telefone","Digite seu telefone:"));
        assertEquals("Digite seu nome:",form.getCampo("nome").getNome());

        // Um campo só é preenchido quando é definido um valor
        assertFalse(form.getCampo("nome").isPreenchido());
        form.getCampo("nome").setValor("Joao da Silva");
        assertTrue(form.getCampo("nome").isPreenchido());
    }
}
