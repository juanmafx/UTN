import java.util.*;
public class condi
{ 
public static void main(String[] args)

/* 
Programa que pida las tres notas de un alumno en cualquier materia y mostrar 
si el alumno esta libre, regular o promocionado. 
Las tres notas son los dos parciales mas la nota de prácticos y 
las condiciones de regularidad están descriptas a continuación:
El promedio menor a 4 el alumno esta libre
El promedio comprendido entre 4 y 8 el alumno esta regular
El promedio mayor a 8 el alumno está promocionado.
 */


{
Scanner sc = new Scanner(System.in);
double prom = 0;

System.out.println("Nota primer parcial :");
int pp = sc.nextInt();
System.out.println("Nota segundo parcial:");
int sp = sc.nextInt();
System.out.println("Nota tp:");
int tp = sc.nextInt();

prom = (pp + sp + tp )/3;

if (prom >= 8)
System.out.println("El alumno esta promocionado con un promedio de " + prom + ".");
if ( (prom>=4) && (prom<8) )
System.out.println("El alumno esta Regular con un promedio de " + prom + ".");
if (prom<4)
System.out.println("El alumno esta libre con un " + prom + ".");


}
}
