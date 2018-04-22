nimport java.util.*;
java.util.Random;
java.security.SecureRandom;
public class ej11

/*
Realizar un programa que genere 15 números aleatorios enteros
 en el rango del 1 al 100, que representaría la tarjeta de bingo de una persona. 
 Una vez generado los números aleatorios 
 solicitar al usuario que ingrese 3 números enteros 
 a partir de allí mostrar los siguientes mensajes:
•	Si el usuario no marco ninguno de los números indicarlo diciendo "El jugador tiene mala suerte, no marco ninguna casilla"


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
