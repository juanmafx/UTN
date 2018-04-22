import java.util.*;
public class comercio7
{ 
public static void main(String[] args)

/*
7 Comercio
3 tipos de artículos.
 Conociendo la cantidad vendida de cada artículo
Precio unitario de cada artículo, 
determine  el producto que realizó el mayor aporte 
Ese porcentaje se calcula así:
Absoluto ____________  100%
Mayor aporte_________     x %
 Por lo tanto:    x = mayor aporte  *  100 / absoluto
*/
{
Scanner sc = new Scanner(System.in);
double it1=0;
double it2=0;
double it3=0;
double it=0;
double ma=0;

System.out.println("Nombre producto Uno");
String np1 = sc.next();
System.out.println("Precio de " + np1);
double pp1 = sc.nextDouble();
System.out.println("Cantidades de unidades de " + np1);
double ct1 = sc.nextDouble();

System.out.println("Nombre producto Dos");
String np2 = sc.next();
System.out.println("Precio de " + np2);
double pp2 = sc.nextDouble();
System.out.println("Cantidades de unidades de " + np2);
double ct2 = sc.nextDouble();

System.out.println("Nombre producto tres");
String np3 = sc.next();
System.out.println("Precio de " + np3);
double pp3 = sc.nextDouble();
System.out.println("Cantidades de unidades de " + np3);
double ct3 = sc.nextDouble();

it1=pp1*ct1;
it2=pp2*ct2;
it3=pp3*ct3;
it = it1+it2+it3;

if ( (it1>it2) && (it1>it3) )
{
	ma=it1*100/it;
	System.out.println(np1+ "Fue el producto con mayor aporte.\nCon una participacion del "+ma+"% .");
}

if ( (it2>it1) && (it2>it3) )
{
	ma=it2*100/it;
	System.out.println(np2+ "Fue el producto con mayor aporte.\nCon una participacion del "+ma+"% .");
}

if ( (it3>it1) && (it3>it2) )
{
	ma=it3*100/it;
	System.out.println(np3+ "Fue el producto con mayor aporte.\nCon una participacion del "+ma+"% .");
}









}
}
