/*
Ingresar los datos de un conjuto de almunos para calcular sus promedios
De cada alumno se ingesan 3 notas.

No se conoce la cantidad de alumnos, por lo tanto finaliza con Zero
Por cada alumno mostrar su promedio y su condicion.

prom <4 libre
prom >=4 , <8 regular
prom  > =8 Promocionado


Cantidad de alumnos cargados.
Cantidad de laumnos libres regulares y promocionales.
Promedio general acoum /contado
*/

import java.util.Scanner;
public class aluprom {
public static void main(String[] args) {

float  ca = 0;
float  prom = 0;
float  lib = 0;
float  reg = 0;
float  pro = 0;
float  promg = 0;
float  ppa = 0;
float  spa = 0;
float  tpa = 0;
float	 pp = 0;
float	 sp = 0;
float	 tp = 0;



Scanner sc = new Scanner(System.in);
System.out.println("Ingrese notas del alumno ");
 pp = sc.nextFloat();
 ppa = ppa + pp;
 sp = sc.nextFloat();
 spa=spa+sp;
 tp = sc.nextFloat();
 spt= spt+tp;

 prom = (pp + sp + tp)/3;


while ((pp != 0) || (sp != 0) || ( tp !=0 ) ){
	++ca;

if (prom < 4){
	System.out.println("El alumno esta Libre con un promedio de :" + prom );
	++lib;
	}

if ((prom >= 4) && (prom < 8)){
	System.out.println("El alumno esta Regular con un promedio de :" + prom );
	++reg;
	}

if (prom > 8){
	System.out.println("El alumno esta Promocionado con un promedio de :" + prom );;
	++pro;
	}



 pp = sc.nextFloat();
 tpa= tpa+pp;
 sp = sc.nextFloat();
 spa = spa+sp;
 tp = sc.nextFloat();
 tpa = tpa +tp;
 
 promg = (ppa +  spa + tpa) / ca;
	
}




System.out.println("Cantidad de alumnos 	cargados 	= " + ca );
System.out.println("Cantidad de alumnos 	   Libres	= " + lib );
System.out.println("Cantidad de alumnos 	Regulares	= " + reg );
System.out.println("Cantidad de alumnos Promocionados	= " + pro );
System.out.println("Promedio general 	del curso 		= " + promg );




}
}
