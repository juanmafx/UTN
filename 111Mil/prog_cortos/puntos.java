import java.util.*;
public class puntos{
	public static void main(String[] args)
	{
		//Asignaciones
		Scanner sc = new Scanner(System.in);
		double tp;
		double promedio;

		//Lecturas
		System.out.println("Total de partidos Ganados :");
		double partidosGanados = sc.nextDouble();
		System.out.println("Total de partidos Empatados :");
		double partidosEmpatados = sc.nextDouble();
		System.out.println("Total de partidos Perdidos:");
		double partidosPerdidos = sc.nextDouble();

		//Operaciones
		tp = partidosGanados + partidosPerdidos + partidosPerdidos;
		promedio  = ((partidosGanados*3) + (partidosEmpatados*2) + (partidosPerdidos* 0))/tp;

		//Mostrar de resultados/salida.
		System.out.println("Total de partidos  = " + tp);
		System.out.println("Promedio de puntos para el decenso  = " + promedio);
	}
}