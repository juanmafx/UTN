package pares_de_un_vectgor;

import java.util.Scanner;

public class Pares_de_un_vectgor {

    public static void main(String[] args) {

        Scanner cs = new Scanner(System.in);
        int cant;
        int n;
        int i;
        int j;
        int k;
        int l;
        int o;
        int vec[];
        double prom;
        float acu = 0;

        System.out.println("Ingrese cantidad de enteros :");
        cant = cs.nextInt();
        vec = new int[cant];

        for (i = 0; i < vec.length; i++) {
            System.out.println("Ingrese Un Valor :");
            vec[i] = cs.nextInt();
        }

        for (j = 0; j < cant; j++) {

            System.out.print(vec[j]);
        }
        System.out.println("");

        for (k = 0; k < vec.length; k++) {
            acu = acu + vec[k];

        }
        System.out.println("");
        prom = acu / cant;

        System.out.println("El promedio del vector es " + prom);

        System.out.println("");
        System.out.println("Los enternos del Vector mayor al promedio sos: ");
        for (l = 0; l < cant; l++) {

            if (vec[l] > prom) {
                System.out.print(vec[l]);

            }

        }

        System.out.println("");
        System.out.println("Los enternos del Vector menor al promedio  Sons: ");
        for (o = 0; o < cant; o++) {

            if (vec[o] < prom) {
                System.out.print(vec[o]);

            }

        }

    }
}
