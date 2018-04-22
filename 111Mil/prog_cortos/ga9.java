import java.util.*;
public class ga9
{ 
public static void main(String[] args)
/*
Se realiza una prueba con tres cuadros
 y por cada uno se ingresa el año en que fue creado. 
 El programa deberá:
 
1.	Verificar si todos los cuadros son anteriores al siglo XX 
(El siglo XX es el siglo pasado.
Se inició en el año 1901 y terminó en el año 2000).

2.	Determinar cuántos tienen antigüedad inferior a 10 años. 
Si no hay ninguno, imprimir el mensaje Renovar stock.
*/
{
Scanner sc = new Scanner(System.in);
int year = Calendar.getInstance().get(Calendar.YEAR);
int cn=0;



System.out.println("Nombre del cuadro uno ");
String c1 = sc.next();
System.out.println("Anio de creacio el cuadro" + c1 + ".");
double a1 = sc.nextDouble();

System.out.println("Nombre del cuadro dos ");
String c2 = sc.next();
System.out.println("Anio de creacion del cuadro" + c2 + ".");
double a2 = sc.nextDouble();

System.out.println("Nombre del cuadro tres ");
String c3 = sc.next();
System.out.println("Anio de creacion del cuadro" + c3 + ".");
double a3 = sc.nextDouble();

if ((a1<2000)&&(a2<2000)&&(a3<200))
	System.out.println("Todos los cuaadros son inferior al siglo XX");


if ( (a1)>(year-10) )
	cn=cn+1;

if ( (a2)>(year-10) )
	cn=cn+1;
if ( (a3)>(year-10) )
	cn=cn+1;

if(cn>=0)
	System.out.println("Renovar stock porque se tienen al menos "+ cn +" cuadros con menos de 10 anios ");

	






}
}
