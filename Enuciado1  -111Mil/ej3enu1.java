/*
3 Calculo de Regularidad
La facultad pide un simple programa que pida las tres notas de un alumno en 
cualquier materia y mostrar si el alumno esta libre, regular o promocionado. 
Las tres notas son los dos parciales mas la nota de prácticos y las condiciones de 
regularidad están descriptas a continuación:
El promedio menor a 4 el alumno esta libre
El promedio comprendido entre 4 y 8 el alumno esta regular
El promedio mayor a 8 el alumno está promocionado.
*/

import java.util.*;
public class ej3enu1{
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
int prom;

System.out.println("Nota Primer Parcial :");
int p1 = sc.nextInt();
System.out.println("Nota segundo Parcial:");
int p2 = sc.nextInt();
System.out.println("Nota trabajo Practico:");
int tp = sc.nextInt();

prom = (p1+p2+tp)/3;

if (prom<4)
	System.out.println("El alumno esta LIBRE con un promedio de "+ prom +".");


if ((prom>4)&&(prom<8))
	System.out.println("El alumno esta REGULAR con un promedio de "+ prom +".");

else
	System.out.println("El alumno esta POMOCIONADO con un promedio de "+ prom +".");

}
}