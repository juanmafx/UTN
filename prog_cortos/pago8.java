import java.util.*;
public class pago8
{ 
public static void main(String[] args)
/*
8 Pago a un Proveedor
Ingresar:
+categoría 'A' o 'B'  
+importe original a abonar.

Si el cliente es categoría A
y el monto a pagar supera a los 1000 pesos 
debe aplicarse un descuento del 5%.

Si el cliente es categoría B 
el importe a pagar oscila entre 1500 y 2500 pesos 
debe aplicarse un descuento del 2%.

Para ambas categorías 
en caso de no cumplirse las condiciones especificadas 
no se aplicará ningún tipo de descuento.
map= monto a pagar
*/
{
Scanner sc = new Scanner(System.in);
double desca=0.05;

System.out.println("Categoria Cliente  ingrese o *A* o *B*");
char cate = sc.nextChar();
System.out.println("Ingrese monto a pagar del cleinte categoria " + cate + ".");
double map = sc.nextDouble();

if(cate=='a')
map=map-(desca*map);

System.out.println("Monto a pagar es de " + map + " pesos.");




}
}
