/*
Este primer Desafío es sencillo y lo asumimos como una forma de 
"entrada en calor" 
para enfrentar este tipo de evaluaciones. 
El problema o consigna, es el siguiente:
Desarrolle un programa Java 
que permita cargar por teclado un número entero 
que representa la  cantidad de segundos que pasaron desde un evento dado.
El programa debe convertir esa cantidad de segundos  
a la cantidad de horas, minutos y segundos que transcurrieron. 
Por ejemplo, si la cantidad de segundos  ingresada es 4452 
deberá mostrar un mensaje que informe que el tiempo transcurrido fue de 1 hora, 14 minutos  y 12 segundos.

Su tarea: 
cuando haya desarrollado el programa pedido, ejecútelo tres veces, y 
compruebe los resultados que obtenga al 
cargar las siguientes cantidades de segundos:
Primera ejecución - Cantidad de segundos a ingresar:  5733
Segunda ejecución - Cantidad de segundos a ingresar:  8901
Tercera ejecución - Cantidad de segundos a ingresar:  15734
Cuarta ejecución - Cantidad de segundos a ingresar: 897
Cuando haya obtenido los resultados, ingrese en este mismo desafío, 
y allí se le pedirá que registre los resultados en formato 
"horas:minutos:segundos". Tomando el mismo ejemplo que se indica en el 
enunciado del problema, si la cantidad de segundos ingresada es 4452, 
entonces usted deberá subir un resultado de la forma 1:14:12 sin espacios en blanco, 
sin comillas, y usando estrictamente el caracter : (dos puntos) 
para separar una parte de la otra. Cualquier error que cometa en el formato 
de su respuesta, podrá hacer que la solución subida se considere incorrecta, 
así que hágalo con mucho cuidado. 
Si la cantidad de horas o de minutos o de segundos final fue igual a cero, 
registre un cero en esa posición. Por ejemplo, 
si usted obtuvo una respuesta de 0 horas, 23 minutos y 0 segundos, 
entonces registre la siguiente respuesta: 0:23:0 cuando le sea requerida.

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
public class des01a{
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
int hh;
int mm;

System.out.println("Ingrese la duracion del evento en segundos :");
int ss = sc.nextInt();

hh=(ss/60)/60;
mm=(ss/60)-(hh*60);
ss=ss-(mm*60)-(hh*60*60);

System.out.println(hh+":"+mm+":"+ss);

}
}
