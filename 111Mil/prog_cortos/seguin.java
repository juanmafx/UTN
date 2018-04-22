import java.util.*;
public class seguin
{ 
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);

System.out.println("Ingrese horas del evento:");
int hs = sc.nextInt();
System.out.println("Ingrese minutos del evento:");
int min = sc.nextInt();
System.out.println("Ingrese segundos del evento:");
int seg = sc.nextInt();

seg= (hs*60*60) + (min*60) + (seg);

System.out.println("Total de segundos :" + seg);

}

}
