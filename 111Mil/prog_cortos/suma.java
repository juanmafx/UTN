import java.util.*;
public class suma
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);
int t;
int total;

System.out.println("Ingrese N1 :");
int n1 = sc.nextInt();
System.out.println("Ingrese N2 :");
int n2 = sc.nextInt();
System.out.println("Ingrese N3 :");
int n3 = sc.nextInt();


t = n1 + n2 + n3;

if (t>10) total= t/2;
else total = t*t*t;


System.out.println(total);
		}
	}