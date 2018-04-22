/*
En una competencia deportiva cada participante acumula puntos según su desempeño.
 Los organizadores de dicha competencia nos solicitan un programa que permita que se 
ingrese el resultado final una vez concluida la misma y que ofrezca las siguientes salidas impresas:

Cantidad total de competidores.
Promedio de puntaje obtenido por los competidores.
Cantidad de competidores con puntaje menor a 10.
Nombre del competidor con mayor cantidad de puntos.

Para ello se dispone de las siguientes entradas, una por cada competidor: 

Número: número entero que identifica a cada competidor
Nombre: cadena de caracteres con el nombre del competidor
Puntaje obtenido: número entero que indica cuántos puntos obtuvo

Realice una prueba de escritorio con los siguientes datos:
Número  Nombre  Puntaje
2 Rosa 14
9 Alejandro 16
23 Julieta 5
20 Ramiro 4
15 Susana 10
6 Juan 20
0
 
 */
import java.util.*;

public class Cdepo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double num;
        String nom;
        String win = "";
        double pun;
        double cp = 0;
        double acup = 0;
        double pm10 = 0;
        double pmay = 0;
        double prom = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese Numero : ");
        num = sc.nextDouble();
        System.out.println("Ingrese Nombre : ");
        nom = sc.next();
        System.out.println("Ingrese Puntaje : ");
        pun = sc.nextDouble();

        while (num != 0) {
            cp++;
            acup = acup + pun;

            if (pun < 10) {
                pm10++;
            }

            if (cp == 0 || pmay < pun) {
                pmay = pun;
                win = nom;
            }

            System.out.println("Ingrese Numero :");
            num = sc.nextDouble();
            System.out.println("Ingrese Nombre :");
            nom = sc.next();
            System.out.println("Ingrese Puntaje :");
            pun = sc.nextDouble();
        }

        prom = acup / cp;

        System.out.println("Cantidad total de competidores. " + cp);
        System.out.println("Promedio de puntaje obtenido por los competidores. " + prom);
        System.out.println("Cantidad de competidores con puntaje menor a 10. " + pm10);
        System.out.println("Nombre del competidor con mayor cantidad de puntos. " + win);

    }

}
