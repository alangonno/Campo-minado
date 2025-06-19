package br.com.cm.modelo;

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


    Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {


    }


}
