import java.util.*;
public class ej1{

/*
1 Suma - División - Potencia
Se necesita desarrollar un programa que permita calcular la suma de tres números. 
Si el resultado es mayor a 10 dividir por 2 
(mostrar su resultado sin decimales)
caso contrario elevar el resultado al cubo.
*/

public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);

System.out.println("Ingrese los 3 numeros:");
double n1 = sc.nextDouble();
double n2 = sc.nextDouble();
double n3 = sc.nextDouble();


if ((n1+n2+n3)>10)
System.out.println(	(int)((n1+n2+n3)/2)	); 

else
System.out.println(	(int)((n1+n2+n3)*(n1+n2+n3)*(n1+n2+n3))	);


}
}