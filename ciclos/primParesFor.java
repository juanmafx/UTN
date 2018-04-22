/* 
Utilizando FOR.
Programa que genere los primeros n números pares y los vaya
mostrando y al final muestre la sumatoria de los mismos.
*/

import java.util.Scanner;
public class primParesFor {
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
int cont = 0;
int acum = 0;
int n = 2;

System.out.println("Ingrese el N hasta donde mostrara los pares: ");
int cant = sc.nextInt();

for (cont = 0 ; cont < cant ; cont ++){
	System.out.println(n);
	acum = acum + n;
	n = n + 2;
	}

System.out.println("La suma Total de todos los numeros es -->  " + acum);
    
}
}
