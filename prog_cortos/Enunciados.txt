import java.util.*;
public class asado{ 
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		//asignaciones
		double totalAsado ;
		double map1;
		double map2;
		double map3;
		System.out.println("Gastado por amigo 1 :");
		double gam1 = sc.nextDouble();		
		System.out.println("Gastado por amigo 2 :");
		double gam2 = sc.nextDouble();		
		System.out.println("Gastado por amigo 3 :");
		double gam3 = sc.nextDouble();

		
		//operaciones
		totalAsado = gam1 + gam2 + gam3;
		map1 = (totalAsado /3 ) - gam1;
		map2 = (totalAsado /3 ) - gam2;
		map3 = (totalAsado /3 ) - gam3;

		//mostrar el resultado
		System.out.println("Monto balance amigo 1 :"+ map1);
		System.out.println("Monto balance amigo 2 :"+ map2);
		System.out.println("Monto balance amigo 3 :"+ map3);
	}
}