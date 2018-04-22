/*
Además, el desafío incluye al final una quinta consigna o pregunta adicional, 
en la cual se le pedirá hacer un programa que 
haga el proceso inverso: deberá tomar tres datos, 
que serán el valor en horas, el valor en minutos y el valor en segundos transcurridos 
desde un evento dado, y su programa deberá calcular la cantidad total de segundos a 
partir de esos datos. 
Por ejemplo, si los datos ingresados fuesen: horas = 4, minutos = 36 y segundos = 8 
entonces el resultado a obtener es que la cantidad total de segundos es 16568.
*/ 

import java.util.*;
public class des01b{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);

System.out.println("Ingrese cantidad de HORAS :");
int hh = sc.nextInt();
System.out.println("Ingrese cantidad de MINUTOS :");
int mm = sc.nextInt();
System.out.println("Ingrese cantidad de SEGUNDOS :");
int ss = sc.nextInt();

ss=(hh*60*60)+(mm*60)+ss;

System.out.println("Cantidad total de segundos es de : "+ ss + " segundos.");

}
}
