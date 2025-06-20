package br.com.cm.modelo;

import br.com.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public void abrir(int coluna, int linha) {
        try {
            campos.parallelStream()
                    .filter(c -> c.getLinha() == linha &&
                            c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());
        } catch (ExplosaoException e) {
            campos.parallelStream()
                    .forEach(campo -> campo.setAberto(true));

            throw e;
        }

    }

    public void alternarMarcarcao(int coluna, int linha) {
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha &&
                        c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
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
        long minasArmadas = 0;
        Predicate<Campo> minado = campo -> campo.isMinado();
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();

        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar() {
        campos.stream().forEach(campo -> campo.reiniciar());
        sortearMinas();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        sb.append(" ");
        for (int l = 0; l < colunas; l++) {
            sb.append(" ");
            sb.append(l);
            sb.append(" ");
        }
        sb.append("\n");

        for (int linha = 0; linha < linhas; linha++) {
            sb.append(linha);
            for (int coluna = 0; coluna < colunas; coluna++) {
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
