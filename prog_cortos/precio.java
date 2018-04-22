import java.util.*;
public class precio{ 
	public static void main(String[] args)
	{
		//Declaraciones de Variables
		double precioDeVentaA;
		double precioDeVentaB;
		double precioDeVentaC;
		double marcacion = 1.2;
		//Crear el escaner 
		Scanner sc = new Scanner(System.in);
		
		//Carga de Datos y carteles
		System.out.println("Ingrese costo de A: ");
		double precioA = sc.nextDouble();		
		System.out.println("Ingrese costo de B: ");
		double precioB = sc.nextDouble();		
		System.out.println("Ingrese costo de C: ");
		double precioC = sc.nextDouble();
		
		//operaciones
		precioDeVentaA=precioA*marcacion;
		precioDeVentaB=precioB*marcacion;
		precioDeVentaC=precioC*marcacion;
		
		//mostrar el resultado precentacion de resultados
		System.out.println("El precio de venta de A = " + precioDeVentaA + ".");
		System.out.println("El precio de venta de B = " + precioDeVentaB + ".");
		System.out.println("El precio de venta de C = " + precioDeVentaC + ".");
	}
}