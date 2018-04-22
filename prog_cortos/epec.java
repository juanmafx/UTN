import java.util.*;
public class epec{ 
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		double tasa = 5;
		double iva = 0.21;
		double costoDiario = 1.2;
		double imp = 0.5;
		double va = 0;
		double total;
		double pretotal;
		double diasdelmes;
		double diasRestantes;
		

		//asignaciones
		System.out.println("Consumo mes anterior: ");
		double c = sc.nextDouble();
		System.out.println("Consumo que indica ahora: ");
		double indi = sc.nextDouble();
		System.out.println("Numero De Dia Actual: ");
		double nd = sc.nextDouble();
		
		//operaciones
		diasRestantes = 30 - nd;
		va = indi - c;
		pretotal =( va*2 );
		total = pretotal +(pretotal*iva)+(pretotal*imp)+(tasa)+(diasRestantes * 1.2);
		
		//mostrar el resultado
		System.out.println("Monto a pagar " + total);

	}
}