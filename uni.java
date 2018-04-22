import java.util.*;
public class uni
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);
int c;
int d;
System.out.println("Ingrese unidades :");
int u = sc.nextInt();

c = u/100;
d = u/10 - (c*10);
u = u - (c*100) - (d*10);


System.out.println("Centenas : "+c+ " Decenas :"+d+" Unidades : "+u);
		}
	}