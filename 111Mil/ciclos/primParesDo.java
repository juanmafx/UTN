/* 
Utilizando DO.
Programa que genere los primeros n números pares y los va
mostrando y al final muestre la sumatoria 
*/

import java.util.Scanner;
public class primParesDo {
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
int cont = 0;
int acum = 0;
int n = 2;

System.out.println("Ingrese el N hasta donde mostrara los pares: ");
int cant = sc.nextInt();

do{
	System.out.println("Par :  " + n);
	cont++;
	acum = acum + n;
	n += 2;
}
while (cont < cant);

System.out.println("La suma Total de todos los numeros es -->  " + acum);

    
}
    
}
