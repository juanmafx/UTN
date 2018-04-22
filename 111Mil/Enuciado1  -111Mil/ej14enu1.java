import java.util.Scanner;
public class ej14enu1 {
public static void main(String[] args) {

Scanner sc = new Scanner(System.in);
double imc= 0;

System.out.println("Ingrese peso en Kg de la persona.");
double p = sc.nextDouble();
System.out.println("Ingrese altura en MM de la persona.");
double a = sc.nextDouble();

imc= (p / (a*a) );

while (p != 0){
	if (imc <= 16)
	System.out.println("IMC = "+ imc + "Necesita asistencia de un medico, los riesgos para su salud son muy altos .");
if (imc <= 17)
	System.out.println("IMC = "+ imc + " Usted tiene infrapeso, alimentese medico .");
if (imc <= 18)
	System.out.println("IMC = "+ imc + " Usted tiene bajo peso, alimentese mejor .");
if ((imc > 18) && (imc <= 26))
	System.out.println("IMC = "+ imc + "  Usted tiene un peso saludable, continúe así! .");
if ((imc > 26) && (imc < 30 ))
	System.out.println("IMC = "+ imc + " Tiene sobrepeso de grado I, hoy es un buen día para empezar a hacer ejercicios .");
if ((imc >= 30) && (imc <= 35))
	System.out.println("IMC = "+ imc + " Tiene obesidad de grado II, necesita el apoyo de un plan nutricional ");
if ((imc > 35) && (imc <= 40 ))
	System.out.println("IMC = "+ imc + " Tiene obesidad grado III (pre-mórbida), consulte con su medico los riesgos para su salud .");
if (imc >40)
	System.out.println("IMC = "+ imc + " Usted tiene obesidad de grado IV mórbida los riesgos para su salud son muy altos, consulte con su medico a la brevedad ");
;

System.out.println("Ingrese peso en Kg de la persona.");
p = sc.nextDouble();
System.out.println("Ingrese altura en MM de la persona.");
a = sc.nextDouble();

}


}
}

