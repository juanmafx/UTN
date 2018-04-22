import java.util.*;
public class cues{ 
	public static void main(String[] args)
	{
Scanner sc = new Scanner(System.in);
int hrs;
int min;
System.out.println("Ingrese la duracion del evento en segundos :");
int seg = sc.nextInt();


hrs= (seg/60)/60;
min= (seg/60) - (hrs*60);
seg= seg - (hrs*60*60) - (min*60);



System.out.println(hrs+":"+min+":"+seg);
	}
}