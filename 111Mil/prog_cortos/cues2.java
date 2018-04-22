import java.util.*;
public class cues2
	{ 
		public static void main(String[] args)
		{
Scanner sc = new Scanner(System.in);
int seg;

System.out.println("Ingrese horas :");
int hrs = sc.nextInt();
System.out.println("Ingrese minutos :");
int min = sc.nextInt();
System.out.println("Ingrese segundos :");
int segu = sc.nextInt();

seg = ((hrs*60)*60) + (min*60)+ segu ;


System.out.println(seg);
		}
	}