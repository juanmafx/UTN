/*
 Cargar las edades de los alumnos ingresanstes de primer ano.
(Fin de carga a eleccion)
Sabiendo que las esdades van entre 17 anos a 32 determinar.
A) Edad promedio.
B) Cantidad de alumnos mayores respecto al promedio.
C) Cual es la edad mas frecuente.
 */
package busquedavector;

import java.util.Scanner;

public class Busquedavector {

    public static void main(String[] args) {

        int cant;
        Scanner sc = new Scanner(System.in);
        int vec[];
        int acu = 0;
        int prom = 0;
        int cont = 0;
        int edad = 0;

        System.out.println("Ingrese cantidad de alumnos : ");
        cant = sc.nextInt();
        vec = new int[cant];

        System.out.println("Ingrese Edades : ");

        for (int i = 0; i < cant; i++) {
            edad = sc.nextInt();

            if (edad > 17 && edad < 32) {
                vec[i] = edad;
            }
            else i--;
                

        }

        for (int i = 0; i < cant; i++) {
            acu = acu + vec[i];
        }
        prom = acu / cant;

        System.out.println("La edad promedio es de = " + prom);

        for (int i = 0; i < cant; i++) {

            if (vec[i] > prom) {
                cont++;

            }

        }

        System.out.println("La cantidad de alumnos mayores al promedio es de =  " + cont);

    }

}
