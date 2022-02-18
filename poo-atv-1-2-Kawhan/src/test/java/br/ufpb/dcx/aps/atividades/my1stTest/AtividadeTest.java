package test.java.br.ufpb.dcx.aps.atividades.my1stTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AtividadeTest {


    Produto produto;
    @BeforeEach
    void setUp() {
        produto = new Produto();
    }

    @Test
    void testProduto() {

        assertEquals("",produto.getNome());
        assertEquals(0.0,produto.getPreco());

        produto.setNome("Sapato");
        assertEquals("Sapato",produto.getNome());
        produto.setDescricao("Sapato social preto");
        assertEquals("Sapato social preto", produto.getDescricao());
        produto.setPreco(100.0);
        assertEquals(100.0,produto.getPreco());

        produto.setPreco(0.1);
        assertEquals(0.1,produto.getPreco());

        Exception e = assertThrows(RuntimeException.class, () ->
                       produto.setPreco(-30.0) );
        assertEquals("preco não pode ser negativo: -30.0", e.getMessage());

    }

}
