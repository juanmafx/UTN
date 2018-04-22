/* 
En una producción de piezas de repuesto para herrería 
se ha medido la longitud delas piezas producidas, 
y se pide un programa que cargue una por una estas longitudes.

Sabiendoque se considera como 
apta a toda pieza que mida entre 1.25 metros y 1.20 metros, 
calcular el promedio de longitud de las piezas aptas;  
la cantidad de piezas no aptas. 
Terminar el procesode carga cuando la longitud ingresada sea cero.
*/

import java.util.Scanner;
public class herreria{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
double pa = 0;
double pna = 0;
double prom = 0;
double acumtp = 0;
double c = 0;
double cq = 0;
double tp =0 ;

System.out.println("Ingrese el TP - Tamanio de la Piesa :  ");
tp = sc.nextDouble();

while (tp != 0){
	c++;

if ((tp >= 1.20) && (tp <= 1.25)){
	cq++;
	acumtp =acumtp +tp;
}
else
pna++;

System.out.println("Ingrese el TP - Tamanio de la Piesa : 
}

prom= acumtp/c;

System.out.println("Cantidad de piezas aptas    --> " + cq);
System.out.println("Cantidad de piezas NO aptas --> " + pna);
System.out.println("Promedio de piezas aptas    --> " + prom);


    
}
}


