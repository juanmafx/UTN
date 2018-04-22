import java.util.*;
public class bintode{
public static void main(String[] args){

long numero, aux, digito, decimal;
Scanner sc = new Scanner(System.in);
int exponente;

System.out.print("Introduce un numero binario: ");
numero = sc.nextLong();

exponente = 0;
decimal = 0;

while (numero != 0) {
//se toma la última cifra
digito = numero % 10;

//se multiplica por la potencia de 2 correspondiente y se suma al número
decimal = decimal + digito * (int) Math.pow(2, exponente);
//se aumenta el exponente
exponente++;
//se quita la última cifra para repetir el proceso
numero = numero / 10;
}

System.out.println("Decimal: " + decimal);

}
}



