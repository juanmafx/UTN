/*
8 Pago a un Proveedor
Un comercio necesita informar el importe final a pagar a un determinado proveedor. 
Para ello debe ingresar la categoría (que puede ser categoría 'A' o 'B') 
y el importe original a abonar.
Considerar las siguientes condiciones para el cálculo del importe final a pagar:

Si el cliente es categoría A 
y el monto a pagar supera a los 1000 pesos 
debe aplicarse un descuento del 5%.

Si el cliente es categoría B y el importe a pagar oscila entre 1500 y 2500 pesos 
debe aplicarse un descuento del 2%.
Para ambas categorías en caso de no cumplirse las condiciones especificadas 
no se aplicará ningún tipo de descuento sobre el importe que se le debe abonar.
*/

import java.util.*;
public class ej8enu1{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
double desa=0.05;
double desb=0.05;

System.out.println("Ingrese Categoria del Cliente type o *A* o *B*: ");
char cat = sc.next().charAt(0);
System.out.println("Ingrese monto a pagar del cliente categoria " + cat + ".");
double map = sc.nextDouble();


if(	(cat=='a')	&&	(map>1000)	)
map=map-(desa*map);

if(	(cat=='b')	&&	(((map > 1500)&&(map < 2500)))	)
map=map-(desb*map);

System.out.println("Monto a pagar es de " + map + " pesos.");

}
}
