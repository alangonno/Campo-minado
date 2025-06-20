package br.com.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int colunas, int minas, int linhas) {
        this.colunas = colunas;
        this.minas = minas;
        this.linhas = linhas;

        gerarCampos();
        associarOsVizinhos();
        sortearMinas();
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas ; linha++) {
            for (int coluna = 0; coluna < colunas ; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
            
        }
    }

    private void associarOsVizinhos() {
        for (Campo c1: campos) {
            for (Campo c2: campos) {
                c1.adicionarVizinho((c2));
            }
        }
    }
    
    private void sortearMinas() {

    }
}
