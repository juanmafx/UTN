/*
6 Jornal de un Operario
Se necesita desarrollar un programa para el área de recursos humanos de una empresa 
que permita informar el jornal de un determinado operario. 
Usted deberá cargar por teclado el código de turno que el operario trabajó ese día 
(1- representa Diurno y 2- representa Nocturno) 
y la cantidad de horas trabajadas.
La política de trabajo en la empresa es que los operarios de la misma pueden trabajar 
en el turno diurno o nocturno. 
Si un operario trabaja en el turno nocturno el pago es 40.60 pesos la hora, 
si lo hace en el turno diurno cobra 35.50 pesos la hora.
*/

import java.util.*;
public class ej6enu1{
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
double hd = 40.60;
double hn = 35.50;
String jornadac = "";
double salario = 0;

System.out.println("Ingrese Codigo del turno :\n **1- representa Diurno \n **2- representa Nocturno");
int jornada = sc.nextInt();

if (jornada==1)
	jornadac="Turno Diurno";
if (jornada==2)
	jornadac="Turno Nocturno";

System.out.println("Ingrese cantidad de horas trabajadas en el " + jornadac);
int ht = sc.nextInt();

if (jornada==1){
	jornadac ="Turno Diurno";
	salario  =hd*ht;
	}
	
if (jornada==2){
	jornadac ="Turno Nocturno";
	salario  =hn*ht;
	}
	
System.out.println("El salariio a cobrar  es de pesos : - > " + salario + ".");

}
}
