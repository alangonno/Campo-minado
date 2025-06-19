package br.com.cm.modelo;

import br.com.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;
    private int bombaEmArea = 0;

    private List<Campo> vizinhos = new ArrayList<Campo>();

    private final int linha;
    private final int coluna;


    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho) { // Vizinho Esta em volta ou Não

        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;
        int delta = Math.abs(linha+coluna) - (vizinho.coluna +vizinho.linha);

        if (!diagonal && delta == 1) {
            vizinhos.add(vizinho);
            return true;

        } else if (diagonal && delta == 2) {
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }

    }

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    boolean abrir() {
        if (!marcado && !aberto) {
            aberto = true;

            if (minado){
                throw new ExplosaoException("KABUMMMM!!!");
            }

            if(vizinhancaSegura()) {
                vizinhos.forEach(vizinho -> vizinho.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(campoVizinho -> campoVizinho.minado);
    }


}
