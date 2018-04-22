import java.util.*;
public class impuesto

/*   Impuesto Automotor
Programa que permita calcular los impuestos a pagar de un auto, 
conociendo su modelo (año de fabricación) 
y tipo (P: Particular/T: Taxi/R: Remis). 
Para calcular los impuestos, tener en cuenta que:
Los autos particulares de menos de 10 años de antigüedad pagan $200
Entre 10 y 20 años pagan $150.
No pagan impuestos los que tienen más de 20 años.

Los taxis pagan impuestos como auto particular.
	Más $150 por la licencia de taxi.
	
Los remises pagan $100 por cada año de antigüedad de su vehículo
*/
{ 
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
int year = Calendar.getInstance().get(Calendar.YEAR);
String tvd ="";
int antig;
int lt=150;
int imp = 0;


System.out.println("Ingrese Tipo de vehiculo \nPARTICULAR INGRESE numero --> 1 \n   TAXI    INGRESE numero --> 2 \n   REMIS   INGRESE numero --> 3");
int tv = sc.nextInt();
System.out.println("Ingrese Modelo del coche  en formato aaaa ejemplo 1980 : ");
int modelo = sc.nextInt();

if(tv==1) tvd="Particular";
if(tv==2) tvd="Taxi";
if(tv==3) tvd="Remis";

antig = year - modelo ;

if ( (antig <= 10) && (tvd=="Particular") )
	imp= 200;
if ( ( (antig >= 10) && (antig < 20) ) && (tvd=="Particular") )
	imp= 150;
if ( (antig >= 20)  && (tvd=="Particular") )
	imp= 0;

if ( (antig <= 10) && (tvd=="Taxi") )
	imp= 200 + lt ;
if ( ( (antig >= 10) && (antig < 20) ) && (tvd=="Taxi") )
	imp= 150 + lt;
if ( (antig >= 20)  && (tvd=="Taxi") )
	imp= 0 + lt;

if ( (tvd=="Remis") )
	imp= antig * 150;


System.out.println("\n El vehiculo " + tvd + " con una  antiguedad de " + antig + " anios al anio "+  year + "\n Paga un Impuesto de -->  " + imp + " Pesos");









}

}
