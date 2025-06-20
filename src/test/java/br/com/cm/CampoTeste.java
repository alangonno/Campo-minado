package br.com.cm;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.cm.excecao.ExplosaoException;
import br.com.cm.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
         campo = new Campo(3,3);
    }

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(4,3);

        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1,2);

        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testePadraoMarcacao(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcacaoDuas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinaNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMacado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> campo.abrir());
    }

    @Test
    void testeAbrirComVizinhos1(){
        Campo vizinho1 = new Campo(2,2);
        Campo vizinhoDoVizinho1 = new Campo(1,1);

        vizinho1.adicionarVizinho(vizinhoDoVizinho1);

        campo.adicionarVizinho(vizinho1);

        campo.abrir();

        assertTrue(vizinho1.isAberto() && vizinhoDoVizinho1.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2(){
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1, 2);
        Campo campo22 = new Campo(2,2);

        campo12.minar();

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && !campo11.isAberto());
    }

}
