package br.ufpb.dcx.aps.strategy.form;


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
        assertTrue(form.getItensFormulario().isEmpty());

        form.setTitulo("Titulo");
        assertEquals("Titulo", form.getTitulo());
    }

    @Test
    void testCampos() {
        form.addItemFormulario(new Campo("nome","Digite seu nome:"));
        form.addItemFormulario(new Campo("email","Digite seu email:"));
        form.addItemFormulario(new Campo("telefone","Digite seu telefone:"));
        assertEquals(3,form.getItensFormulario().size());

        //Um formulário não pode ter mais de um campo com o mesmo id
        Exception e = assertThrows(Exception.class, () ->
                form.addItemFormulario(new Campo("nome","Digite seu nome:")));
                assertEquals("'nome' já existe", e.getMessage());
    }



    @Test
    void testValidacaoFormularioCasoSemErro() {
        // um formulário vazio não deve dar erro.
        assertFalse(form.validar().isErro());

        //nenhum campo obrigatório, nenhum campo preenchido, nenhum erro.
        form.addItemFormulario(new Campo("nome","Digite seu nome:"));
        form.addItemFormulario(new Campo("email","Digite seu email:"));
        form.addItemFormulario(new Campo("telefone","Digite seu telefone:"));
        Resultado resultado = form.validar();
        assertFalse(resultado.isErro());
        assertTrue(resultado.getMensagens().isEmpty());
    }

    @Test
    void testValidacaoFormularioCasoComErro_Caso1() {

        //Campo nome obrigatório:
        form.addItemFormulario(new Campo("nome",true,"Digite seu nome:"));
        form.addItemFormulario(new Campo("email","Digite seu email:"));
        Resultado resultado = form.validar();
        assertEquals(1,resultado.getMensagens().size());
        assertEquals("nome: nome é obrigatório e não foi preenchido",resultado.getMensagens().get(0));

        form.getItemFormulario("nome").setValor("Agora tem valor");
        assertFalse(form.validar().isErro());


    }

    @Test
    void testValidacaoFormularioCasoComErro_Caso2() {

        //Campo nome obrigatório:
        form.addItemFormulario(new Campo("nome",true,"Digite seu nome:"));
        form.addItemFormulario(new Campo("email",true,"Digite seu email:"));
        Resultado resultado = form.validar();
        assertEquals(2,resultado.getMensagens().size());
        assertEquals("nome: nome é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("email: email é obrigatório e não foi preenchido",resultado.getMensagens().get(1));

        form.getItemFormulario("nome").setValor("Agora tem valor");
        form.getItemFormulario("email").setValor("Agora tem valor");
        assertFalse(form.validar().isErro());


    }


    /* Muitos campos */
    @Test
    void testValidacaoFormularioCasoComErro_Caso3() {

        for(int i = 0; i < 10 ; i++){
            form.addItemFormulario(new Campo("c"+i,"Campo "+i));
        }

        assertFalse(form.validar().isErro());

        form.getItemFormulario("c3").setObrigatorio(true);

        Resultado resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(1,resultado.getMensagens().size());
        assertEquals("c3: c3 é obrigatório e não foi preenchido",resultado.getMensagens().get(0));

        form.getItemFormulario("c3").setObrigatorio(true);
        form.getItemFormulario("c5").setObrigatorio(true);
        form.getItemFormulario("c7").setObrigatorio(true);

        resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(3,resultado.getMensagens().size());
        assertEquals("c3: c3 é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("c5: c5 é obrigatório e não foi preenchido",resultado.getMensagens().get(1));
        assertEquals("c7: c7 é obrigatório e não foi preenchido",resultado.getMensagens().get(2));

        for(int i = 0; i < 10 ; i++){
            form.getItemFormulario("c"+i).setObrigatorio(true);
        }
        resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(10,resultado.getMensagens().size());
        assertEquals("c0: c0 é obrigatório e não foi preenchido",resultado.getMensagens().get(0));
        assertEquals("c1: c1 é obrigatório e não foi preenchido",resultado.getMensagens().get(1));
        assertEquals("c2: c2 é obrigatório e não foi preenchido",resultado.getMensagens().get(2));
        assertEquals("c3: c3 é obrigatório e não foi preenchido",resultado.getMensagens().get(3));
        assertEquals("c4: c4 é obrigatório e não foi preenchido",resultado.getMensagens().get(4));
        assertEquals("c5: c5 é obrigatório e não foi preenchido",resultado.getMensagens().get(5));
        assertEquals("c6: c6 é obrigatório e não foi preenchido",resultado.getMensagens().get(6));
        assertEquals("c7: c7 é obrigatório e não foi preenchido",resultado.getMensagens().get(7));
        assertEquals("c8: c8 é obrigatório e não foi preenchido",resultado.getMensagens().get(8));
        assertEquals("c9: c9 é obrigatório e não foi preenchido",resultado.getMensagens().get(9));

        for(int i = 0; i < 10 ; i++){
            form.getItemFormulario("c"+i).setValor("Valor do campo "+i);
        }
        resultado = form.validar();
        assertFalse(resultado.isErro());

    }


    @Test
    void testValidacaoCamposNomeEmailIdade() {


        Campo nome = new Campo("nome","Digite seu nome:");
        nome.setValidador(new ValidadorTextoSimples(5,40));
        form.addItemFormulario(nome);


        Campo email = new Campo("email","Digite seu email:");
        email.setValidador(new ValidadorEmail());
        form.addItemFormulario(email);


        Campo idade = new Campo("idade","Digite sua idade:");
        idade.setValidador(new ValidadorInteiro(0,200));
        form.addItemFormulario(idade);

        Resultado resultado = form.validar();
        assertFalse(resultado.isErro());

        // Definindo valores inválidos:
        form.getItemFormulario("email").setValor("emailInvalido");
        form.getItemFormulario("nome").setValor("nn");
        form.getItemFormulario("idade").setValor("-3");

        resultado = form.validar();
        assertTrue(resultado.isErro());
        assertEquals(3,resultado.getMensagens().size());
        assertEquals("nome: ERRO: tamanho do valor < 5: 'nn'",resultado.getMensagens().get(0));
        assertEquals("email: email inválido: 'emailInvalido'",resultado.getMensagens().get(1));
        assertEquals("idade: valor menor que 0:-3",resultado.getMensagens().get(2));

    }



}
