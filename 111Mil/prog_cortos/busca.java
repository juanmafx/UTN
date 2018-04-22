/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscador_de_numer;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Buscador_de_numer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);
        int n = 0;
        int i = 0;
        int[] w;
        int aux;
        int j = 0;
        int k = 0;
        int x = 0;
        int iz = 0;
        int de = n - 1;
        int pos = -1;
        int c;
        int v = 0;

        System.out.println("Ingrese cantidad de enteros :");
        n = cs.nextInt();
        w = new int[n];

        for (i = 0; i < n; i++) {
            System.out.print("Cargue   valor:");
            w[i] = cs.nextInt();
        }

        System.out.println(" Valor a buscar : ");
        x = cs.nextInt();

        while (iz <= de) {
            c = (iz + de) / 2;
            if (x == w[i]) {
                pos = c;
                break;
            } else if (x > w[i]) {
                iz = c + 1;
            } else {
                de = c + 1;
            }
        }
        
        if (pos == -1) 
          System.out.println("No Existe:");
        else 
        System.out.println("Existe " + pos );
        
        
        
    }

}
