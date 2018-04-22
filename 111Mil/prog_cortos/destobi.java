/*
Programa que pasa de binarios a decimales.
Ingresa numero en codigo binario y entraga numero en base 10 (Decimal)
*/
import java.util.*;
public class dectobi{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
long cb = 0;
long td = 0;
long nu = 0;

System.out.println("Ingrese el numero:");
cb = sc.nextLong();
td =  Long.toString(cb).length();




System.out.println("Cantidad de digitos = " + td );
System.out.println("Cantidad de digitos = " + nu );

}
}



