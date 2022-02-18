package br.ufpb.dcx.aps.atividades.atv06;

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

    @Test
    void testValidacaoFormularioCasoSemErro() {
        // um formulário vazio não deve dar erro.
        assertFalse(form.validar().isErro());

        //nenhum campo obrigatório, nenhum campo preenchido, nenhum erro.
        form.addCampo(new Campo("nome","Digite seu nome:"));
        form.addCampo(new Campo("email","Digite seu email:"));
        form.addCampo(new Campo("telefone","Digite seu telefone:"));
        Resultado resultado = form.validar();
        assertFalse(resultado.isErro());
        assertTrue(resultado.getMensagens().isEmpty());
    }

    @Test
    void testValidacaoFormularioCasoComErro_Caso1() {

        //Campo nome obrigatório:
        form.addCampo(new Campo("nome",true,"Digite seu nome:"));
        form.addCampo(new Campo("email","Digite seu email:"));
        Resultado resultado = form.validar();
        assertEquals(1,resultado.getMensagens().size());
        assertEquals("Campo nome: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(0));

        form.getCampo("nome").setValor("Agora tem valor");
        assertFalse(form.validar().isErro());


    }

    @Test
    void testValidacaoFormularioCasoComErro_Caso2() {

        //Campo nome obrigatório:
        form.addCampo(new Campo("nome",true,"Digite seu nome:"));
        form.addCampo(new Campo("email",true,"Digite seu email:"));
        Resultado resultado = form.validar();
        assertEquals(2,resultado.getMensagens().size());
        assertEquals("Campo nome: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("Campo email: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(1));

        form.getCampo("nome").setValor("Agora tem valor");
        form.getCampo("email").setValor("Agora tem valor");
        assertFalse(form.validar().isErro());


    }


    /* Muitos campos */
    @Test
    void testValidacaoFormularioCasoComErro_Caso3() {

        for(int i = 0; i < 10 ; i++){
            form.addCampo(new Campo("c"+i,"Campo "+i));
        }

        assertFalse(form.validar().isErro());

        form.getCampo("c3").setObrigatorio(true);

        Resultado resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(1,resultado.getMensagens().size());
        assertEquals("Campo c3: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(0));

        form.getCampo("c3").setObrigatorio(true);
        form.getCampo("c5").setObrigatorio(true);
        form.getCampo("c7").setObrigatorio(true);

        resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(3,resultado.getMensagens().size());
        assertEquals("Campo c3: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("Campo c5: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(1));
        assertEquals("Campo c7: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(2));

        for(int i = 0; i < 10 ; i++){
            form.getCampo("c"+i).setObrigatorio(true);
        }
        resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(10,resultado.getMensagens().size());
        assertEquals("Campo c0: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("Campo c1: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(1));
        assertEquals("Campo c2: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(2));
        assertEquals("Campo c3: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(3));
        assertEquals("Campo c4: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(4));
        assertEquals("Campo c5: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(5));
        assertEquals("Campo c6: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(6));
        assertEquals("Campo c7: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(7));
        assertEquals("Campo c8: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(8));
        assertEquals("Campo c9: Este campo é obrigatório e não foi preenchido",resultado.getMensagens().get(9));

        for(int i = 0; i < 10 ; i++){
            form.getCampo("c"+i).setValor("Valor do campo "+i);
        }
        resultado = form.validar();
        assertFalse(resultado.isErro());

    }


}
