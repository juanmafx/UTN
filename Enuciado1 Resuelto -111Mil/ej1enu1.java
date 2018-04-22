/*
1 Suma - División - Potencia
Se necesita desarrollar un programa que permita calcular la suma de tres números. 
Si el resultado es mayor a 10 dividir por 2 (mostrar su resultado sin decimales), 
en caso contrario elevar el resultado al cubo.
*/

import java.util.*;
public class ej1enu1{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
int t;
int resultado;

System.out.println("Ingrese N1 :");
int n1 = sc.nextInt();
System.out.println("Ingrese N2 :");
int n2 = sc.nextInt();
System.out.println("Ingrese N3 :");
int n3 = sc.nextInt();

t = n1 + n2 + n3;

if (t>10)
	resultado = t/2;
else 
	resultado = t*t*t;

System.out.println(resultado);


}
}