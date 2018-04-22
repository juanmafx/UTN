import java.util.*;
public class cuadrantes1
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);


System.out.println("Ingrese valor de X :");
int x = sc.nextInt();
System.out.println("Ingrese valor de Y :");
int y = sc.nextInt();

if (x>0)
{
	if (y>0){
		System.out.println("Cuadrante 1");
			}
	else {
		System.out.println("Cuadrante 4");
		}
}
else {
	if (y>0){
	System.out.println("Cuadrante 2");}
	else {
		System.out.println("Cuadrante 4");
	}
	
}

	



		}
	}