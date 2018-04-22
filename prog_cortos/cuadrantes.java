import java.util.*;
public class cuadrantes
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);


System.out.println("Ingrese valor de X :");
int x = sc.nextInt();
System.out.println("Ingrese valor de Y :");
int y = sc.nextInt();

if ((x>0)&&(y>0))
	System.out.println("Pertenece al cuadrante 1 . ");
if ((x<0)&&(y>0))
	System.out.println("Pertenece al cuadrante 2 . ");
if ((x<0)&&(y<0))
	System.out.println("Pertenece al cuadrante 3 . ");
if ((x>0)&&(y<0))
	System.out.println("Pertenece al cuadrante 4 . ");

	


	
		}
	}