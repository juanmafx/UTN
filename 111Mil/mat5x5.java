package vector5x5;

import java.util.*;

public class Vector5x5 {

    public static void main(String[] args) {

        Random r = new Random();
        int fs = 0;

        // Crea la matriz de 
        int m[][];
        m = new int[5][5];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = r.nextInt(4);

            }

        }

        // Miestra la matris
        for (int x = 0; x < m.length; x++) {
            System.out.println("+-  -   -   -   -   -   -+- -   -   -   -   -   -   -+- -   -   -   -   -   -   -   -+----------+");

            for (int y = 0; y < m[x].length; y++) {
                System.out.printf("| %4d   ", m[x][y]);
            }
            System.out.println("|");
        }

        // Sumar Fila
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                fs = fs + m[i][j];
            }
            System.out.println("Suma filas " + fs);
            fs = 0;
        }

    }
