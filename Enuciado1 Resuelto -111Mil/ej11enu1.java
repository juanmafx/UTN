/*
11 Tarjeta de Bingo
Realizar un programa que genere 15 números aleatorios 
enteros en el rango del 1 al 100, 
que representaría la tarjeta de bingo de una persona.
 
Una vez generado los números aleatorios solicitar al usuario que 
ingrese 3 números enteros, a partir de allí mostrar los siguientes 
mensajes: Si el usuario no marco ninguno de los números indicarlo 
diciendo 

"El jugador tiene mala suerte, no marco ninguna casilla"

.
*/
import java.util.*;
import java.util.Random;
public class ej11enu1{
public static void main(String[] args){

List<Integer> numbers = new ArrayList<>(40);
for (int i=1;i<41;i++){
   numbers.add(i);}
   

}
   Random random = new Random();
// Mientras queden cartas en el mazo (en la lista de numbers)
while (numbers.size()>1){
   // Elegimos un índice al azar, entre 0 y el número de cartas que quedan por sacar
   int randomIndex = random.nextInt(numbers.size());

   // Damos la carta al jugador (sacamos el número por pantalla)
   System.out.println("Not Repeated Random Number "+numbers.get(randomIndex));

   // Y eliminamos la carta del mazo (la borramos de la lista)
   numbers.remove(randomIndex);
}


}
}