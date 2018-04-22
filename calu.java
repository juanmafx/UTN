/*
Ingresar las notas  de un conjunto de alumnos .
Finalizar la carga nota = -1

Por cada nota mostrar un mensaje 
menor a 4  	: "Desaprobado"
entre 4 y 6	: "Bueno"
entre 6 y 8 : "Muy bueno"
mayor a  8  : "Exelente"

Los contadores y acumuladores se inicializan en zero en
la mayoroa de los casos.

*/

import java.util.Scanner;
public class calu {
public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.println("Ingrese  calificacion de los alumnos Fin del programa con menos uno");
double n = sc.nextDouble();

int contador= 0;

while (n != -1 ){
if (n < 4)
	System.out.println("Desaprobado");
if ( (n > 4) && (n<6) )
	System.out.println("Muy bueno");
if ( (n > 6) && (n<9) )
	System.out.println("Exelente");
if (n>8)
	System.out.println("Promocionado");

n = sc.nextDouble();
++ contador ;
}


if(n==-1)
	System.out.println("Contador = " + contador);
	



}
}
