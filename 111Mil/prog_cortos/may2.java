import java.util.*;
public class may2
{ 
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
double mayor;

System.out.println("Ingrese dos Numeros:");
double a = sc.nextDouble();
double b = sc.nextDouble();

if (a!=b){
	if (a>b)
		mayor=a;
	else
		mayor=b;
	System.out.println("El mayor es "+mayor);
}
elseSystem.out.println("Son iguales");

}

}
