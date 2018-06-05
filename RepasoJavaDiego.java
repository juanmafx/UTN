/*
Cargar numero en un vector hasta que se ingrese Zero

Luego de cargar informar:
-Cantidad de numeros ingresados.
-Cantidad de positvos.
-Si alguna vez se ingreso el 15
-Promedio de los pares.
-El mayor y cuantas veces se repitio

-Luego de los reportes permitir busquedas en el conjunto cargado.

-Para eso, ingresar numeros para buscarlos,hasatq que se ingrese Zero
-Por cada numero  buscado, informar
-Si se enceçuentra o no :
En caso de encontrarlo, la posicion donde se encuentra


Carga datos desd un file 
scanner sc new=scanner ("numeros.txt")
Scanner archivo = new Scanner("numeros.txt");
Scanner teclado = new Scanner(System.in);
 */
package repasojavadiego;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author alumno
 */
public class RepasoJavaDiego {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Scanner archivo = new Scanner(new File("numeros.txt"));
        float cont = 0;
        float cont_pos = 0;
        boolean esta15 = false;
        float acu_pares = 0;
        float pro_pares = 0;
        float may = 0;
        float contmay = 0;
        float buscado;
        float pos;
        float valido = 0;

        // Crea el Vector de 
        int vec[];
        vec = new int[100];

        for (int i = 0; i < vec.length; i++) {
            cont++;
            vec[i] = archivo.nextInt();
            System.out.println(vec[i]);
            if (vec[i] == 0) {
                break;
            }

        }

        for (int j = 0; j < cont; j++) {

            if (vec[j] > 0) {
                cont_pos++;
            }
        }

        for (int j = 0; j < cont; j++) {
            if (vec[j] == 15) {
                esta15 = true;

            }
        }
        for (int j = 0; j < cont; j++) {
            if (vec[j] % 2 == 0) {
                acu_pares = acu_pares + vec[j];
            }
        }
        may = vec[0];
        for (int j = 0; j < cont; j++) {
            if (vec[j] == may) {
                contmay++;
            }

            if (may < vec[j]) {
                may = vec[j];
                contmay = 1;

            }
        }

        pro_pares = acu_pares / cont;

        // Muestra el Vector
        for (int x = 0; x < cont; x++) {

            System.out.printf("| %4d   ", vec[x]);

        }
        System.out.printf("|");

        //INFORMA
        System.out.println(" ");
        System.out.println("Informe : ");
        System.out.println("Cantidad de numeros ingresados : " + (cont - 1));
        System.out.println("Cantidad de numeros  POSITIVOS  ingresados : " + cont_pos);
        System.out.println("Alguna vez se ingreso el 15 ? = " + esta15);
        System.out.println("Promedio de los PARES ingresados = " + pro_pares);
        System.out.println("El mayor es  = " + may + "Y se ingreso = " + (contmay));

        System.out.println("Ingrese numero que desea buscar");
        buscado = sc.nextInt();

        for (int i = 0; i < vec.length; i++) {
            if (vec[i] == buscado) {
                pos = i;
                System.out.println("El numero " + buscado + " Esta; y esta en la posicion = " + pos);
                valido++;
            }

        }

        if (valido == 0) {
            System.out.println("El numero no esta ");
        }

    }

}
