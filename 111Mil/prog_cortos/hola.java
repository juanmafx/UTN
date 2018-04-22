import java.util.*;
public class hola{ 
	public static void main(String[] args)
	{
		//declaracion
		float kilometros;
		Scanner sc = new Scanner(System.in);
		
		//asignaciones
		System.out.println("Ingrese la cantidad de metros:");
		float metros = sc.nextFloat();
		System.out.println("Ingrese autonomia por metro en kilometros:");
		float kilometrosPorMetro = sc.nextFloat();

		//operaciones
		kilometros=kilometrosPorMetro*metros;
		
		//mostrar el resultado
		System.out.println("La autonomia estimada es de: " + kilometros + "."); 
			  
			  }
}