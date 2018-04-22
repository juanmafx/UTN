import java.util.*;
public class udc
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);
int u;

System.out.println("Ingrese centenas :");
int c = sc.nextInt();
System.out.println("Ingrese decenas :");
int d = sc.nextInt();
System.out.println("Ingrese unidades :");
int uni = sc.nextInt();


u = (c*100) + (d*10)+ uni ;


System.out.println(u);
		}
	}