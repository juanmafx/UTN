/*
 Cargar las edades de los alumnos ingresanstes de primer ano.
(Fin de carga a eleccion)
Sabiendo que las esdades van entre 17 anos a 32 determinar.
A) Edad promedio.
B) Cantidad de alumnos mayores respecto al promedio.
C) Cual es la edad mas frecuente.
 */
package matris;

import java.util.Scanner;

public class Matris {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int j, i;
        int n = 4;
        int m = 5;
        int mat[][];

        mat = new int[n][m];

        System.out.println("Cargue los numeros del arreglo: ");
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.println("Mat[ " + i + "] [" + j + "]");
                mat [i][j]=sc.nextInt();

            }

        }
        
                for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.println("Mat[ " + i + "] [" + j + "]");
                mat [i][j]=sc.nextInt();

            }

        }
        

    }

}
