import java.util.*;
public class jornal6
{ 
public static void main(String[] args)

/* por teclado el código 
 (1- representa Diurno y 2- representa Nocturno) 
 la cantidad de horas trabajadas.
turno nocturno el pago es 40.60 pesos la hora,
 turno diurno cobra 35.50 pesos la hora.
*/
{
Scanner sc = new Scanner(System.in);
double hd = 40.60;
double hn = 35.50;
String jornadac = "";
double salario = 0;

System.out.println("Ingrese Codigo del turno :\n **1- representa Diurno \n **2- representa Nocturno");
int jornada = sc.nextInt();
if (jornada==1)
	jornadac="Turno Diurno";
if (jornada==2)
	jornadac="Turno Nocturno";

System.out.println("Ingrese cantidad de horas trabajadas en el " + jornadac);
int ht = sc.nextInt();

if (jornada==1){
	jornadac ="Turno Diurno";
	salario  =hd*ht;
				}
				
if (jornada==2){
	jornadac ="Turno Nocturno";
	salario  =hn*ht;
				}
System.out.println("El salariio a cobrar  es de :" + salario + " Pesos.");






}
}
