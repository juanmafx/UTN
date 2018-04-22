/*
Ciclo zero a N
Ingresar numeros hasta que se ingrece Zero.

Agrgar contador para los pares.
Agrgar contador para los impares.

Ademas por medio de un acomulador.
De la suma total de todo.


*/

import java.util.Scanner;
public class ePar {
public static void main(String[] args) {

int  n = 0;
Scanner sc = new Scanner(System.in);
System.out.println("Ingrese nuemros Fin con Zero");
 n = sc.nextInt();
int par = 0; 
int imp = 0;
int tot = 0;
int sum = 0;


while (n != 0 ){
	sum = sum + n; // <- - - - Acumulador sum.

if (n%2 == 0){
	System.out.println("El numero " + n + " Es par.");
	++par;  // <- - - - Contador de Pares
	}
else{
	System.out.println("El numero " + n +" Es impar.");
	++imp;	// <- - - - Contador de Impares
	}

n = sc.nextInt();	// <- - - - Se solicita nueva carga Sin esta linea el loop es heterno.
}
tot = par + imp ;


System.out.println("El total de pares   fue de = " +par );
System.out.println("El total de impares fue de = " +imp );
System.out.println("El total de num   cargados = " +tot );
System.out.println("La suma de los numeros cargados = " +sum );



}
}
