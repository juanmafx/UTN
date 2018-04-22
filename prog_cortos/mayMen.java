/*
Mayor de una lista de numeros ingresados
Menor de una lista de numeros ingresados

Este programa utiliza el conceto de bandera por medio
de un contador


*/

import java.util.Scanner;
public class mayMen {
public static void main(String[] args) {


float  x;
float  c = 0;
float  mayor = 0 ;
float  menor = 0;
float stop = 111000;

Scanner sc = new Scanner(System.in);
System.out.println("Ingrese numeros ");
 x = sc.nextFloat();



while (x != (111)  ){
	++c;
	
	if ( (c == 0) || (x > mayor))
		mayor = x;
	
	if ( (c == 0) || (x < menor))
		menor = x;
	
	if ( (menor == 0) && (c != 0))
		menor = x ; 

System.out.println("Ingrese numeros ");
x = sc.nextFloat();


	}


System.out.println("Mayor = " + mayor );
System.out.println("Menor = " + menor );


}
}
