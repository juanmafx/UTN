import java.util.*;
public class bus{ 
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		double cargaRequerida;
		double cargao;

		//asignaciones
		System.out.println("Ingrese su saldo disponible:");
		double saldo = sc.nextDouble();
		System.out.println("Ingrese cantidad de viajes planificados:");
		double qviajes = sc.nextDouble();
		System.out.println("Ingrese precio del pasaje:");
		double ps = sc.nextDouble();
		
		//operaciones
		cargaRequerida = (qviajes* ps) - saldo;
		cargao = cargaRequerida + (ps * 0.8);
		
		
		//mostrar el resultado
		System.out.println("Carga requerida = " + cargao + ".");
	}
}