nimport java.util.*;
public class temperatura{ 
	public static void main(String[] args)
	{
		double tempePromedio;
		Scanner sc = new Scanner(System.in);
		
		//asignaciones
		System.out.println("Ingrese temperatura de la mañana:");
		double temperaturaM = sc.nextDouble();
		System.out.println("Ingrese temperatura de la tarde:");
		double temperaturaT = sc.nextDouble();
		System.out.println("Ingrese temperatura de la noche:");
		double temperaturaN = sc.nextDouble() ;

		//operaciones
		tempePromedio = (temperaturaM + temperaturaT +temperaturaN)/3;
		
		//mostrar el resultado
		System.out.println("Temperatura promedio de ese dia = " + tempePromedio + ".");
	}
}