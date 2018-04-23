
package conradorvector;

import java.util.Random;

public class ConradorVector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random nro = new Random();
        //creo un vector y guardo los 50 numeros generados con random
        int v[] = new int[50];
        int i;
        System.out.print("los numeros generados son: \n");
        for (i = 0; i < v.length; i++) {
            v[i] = nro.nextInt(10);
            System.out.print(v[i] + " ");

        }
        // se crea un vector contador de 10 lugares, para contar cuantas veces se 
        //repite cada número
        int contador[] = new int[10];
        for (i = 0; i < contador.length; i++) {
            contador[i] = 0;
        }

        // recorro el vector con los datos
        for (i = 0; i < v.length; i++) { // segun sea el valor de v[i], se cuenta en el vector contador que hay uno de ese valor
            contador[v[i]] = contador[v[i]] + 1;

        }
        // se muestra los totales
        for (i = 0; i < contador.length; i++) {
            System.out.print("\ntotal de aparicionesdel nro. " + i + " : " + contador[i] + "\n \n");

        }

    }

}
