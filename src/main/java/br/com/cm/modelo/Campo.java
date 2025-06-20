package br.com.cm.modelo;

import br.com.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;
    private final int bombaEmArea = 0;

    private final List<Campo> vizinhos = new ArrayList<>();

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
        int delta = Math.abs((linha+coluna) - (vizinho.coluna + vizinho.linha));

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

    public boolean abrir() {
        if (!marcado && !aberto) {
            aberto = true;

            if (minado){
                throw new ExplosaoException("KABUMMMM!!!");
            }

            if(vizinhancaSegura()) {
                vizinhos.forEach(Campo::abrir);
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(campoVizinho -> campoVizinho.minado);
    }

    public void minar() {
        minado = true;
    }

    public boolean isMarcado() {
        return marcado;
    }
    public boolean isAberto() {
        return aberto;
    }

    public boolean isMinado() {
        return minado;
    }

    public int getBombaEmArea() {
        return bombaEmArea;
    }

    public List<Campo> getVizinhos() {
        return vizinhos;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return  desvendado || protegido;
    }

    long minasNaVizinhanca() {
        return vizinhos.stream().filter(v -> v.isMinado()).count();
    }

    void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString() {
        if(marcado) {
            return"x";
        } else if(aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}
