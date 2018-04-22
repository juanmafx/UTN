import java.util.*;
public class tempe
{ 
public static void main(String[] args)

/*
Ingrese cuatro temperaturas 
informe cuales fueron menores de 20°
*/

{
Scanner sc = new Scanner(System.in);

System.out.println("Ingrese cuatro temeperaturas:");
int t1 = sc.nextInt();
int t2 = sc.nextInt();
int t3 = sc.nextInt();
int t4 = sc.nextInt();

if(t1>20)
System.out.println("La temeratura de la medicion " + t1 + " es menor de 20.");

if(t2>20)
System.out.println("La temeratura de la medicion " + t2 + " es menor de 20.");

if(t3>20)
System.out.println("La temeratura de la medicion " + t3 + " es menor de 20.");

if(t4>20)
System.out.println("La temeratura de la medicion " + t4 + " es menor de 20.");


}

}
