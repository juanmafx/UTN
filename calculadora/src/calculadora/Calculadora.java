package calculadora;

import java.util.*;

public class Calculadora {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;
        double resultado;
        double n1, n2;

        do {
            System.out.println(" 1 Sumar ");
            System.out.println(" 2 Restar ");
            System.out.println(" 3 Multiplicar ");
            System.out.println(" 4 Dividir ");
            System.out.println(" 5 Salir ");
            
            
            do {
            System.out.println(" Ingrese la opcion Valida");
            op = sc.nextInt();
            }
            while (op >5  || op<1 );


            System.out.println("");

            switch (op) {

                case 1:
                    System.out.println("Ingrese N1 :");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese N2 :");
                    n2 = sc.nextDouble();
                    double sum = n1 + n2;
                    System.out.println("Solucion de la Suma :" + sum);

                    break;

                case 2:
                    System.out.println("Ingrese N1 :");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese N2 :");
                    n2 = sc.nextDouble();
                    break;

                case 3:
                    System.out.println("Ingrese N1 :");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese N2 :");
                    n2 = sc.nextDouble();
                    break;

                case 4:
                    System.out.println("Ingrese N1 :");
                    n1 = sc.nextDouble();
                    System.out.println("Ingrese N2 :");
                    n2 = sc.nextDouble();
            }

        } while (op != 5);

    }

}
