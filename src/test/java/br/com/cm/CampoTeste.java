package br.com.cm;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.cm.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
         campo = new Campo(4,4);
    }

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(3,3);

        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(2,2);

        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

}
