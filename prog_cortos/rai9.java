import java.util.*;
public class rai9
{ 
public static void main(String[] args)

/*
10-Enunciado: Raíces de un Polinomio de Segundo Grado
Realizar un programa que permita calcular las raíces de un polinomio de segundo grado y mostrar un mensaje indicando si son reales o imaginarias. Si son reales distintas, mostrar sus dos valores, si son reales iguales, mostrar solo una.
Ayudita: A partir del discriminante, es posible determinar la naturaleza de las raíces de la ecuación (considerando coeficientes reales) y se pueden presentar 3 situaciones:
Si Δ es negativo, ambas raíces son números complejos.
Si Δ es igual a cero, existen dos raíces reales e iguales, por lo tanto hay una solución.
Si Δ es positivo, ambas raíces son reales y distintas.

*/

{
Scanner sc = new Scanner(System.in);

System.out.println("Ingrese cuatro temeperaturas:");
int t1 = sc.nextInt();
int t2 = sc.nextInt();
int t3 = sc.nextInt();
int t4 = sc.nextInt();

if(t1>20)
System.out.println("La temeratura de la medicion " + t1 + " es menor de 20.");

if(t2>20)
System.out.println("La temeratura de la medicion " + t2 + " es menor de 20.");

if(t3>20)
System.out.println("La temeratura de la medicion " + t3 + " es menor de 20.");

if(t4>20)
System.out.println("La temeratura de la medicion " + t4 + " es menor de 20.");


}

}
