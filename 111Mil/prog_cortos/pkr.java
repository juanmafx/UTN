import java.util.*;
public class pkr{ 
public static void main(String[] args){
Scanner sc = new Scanner(System.in);
char c,d,e,t;

System.out.println("Ingrese PALO  c =Corazon  , d= Diamanete, e= Espada , t = Trebol ");
char pal = sc.next().charAt(0);

if(	pal == ('c') 	)
	System.out.println( "El palo es  Corazones ");
if(	pal == ('d') 	)
	System.out.println("El palo es Diamantes ");
if(	pal == ('e') 	)
	System.out.println("El palo es Espadas ");
if(	pal == ('t') 	)
	System.out.println("El palo es Treboles ");

	
System.out.println("Ingrese valor");
int val = sc.nextInt();

if(	val == 14 )
	System.out.println("As");

if(	val == 13 	)
	System.out.println("K");

if(	val == 12 	)
	System.out.println("Q");

if(	val == 11 	)
	System.out.println("J");

if(	val == 10 	)
	System.out.println("10");

if(	val == 9 	)
	System.out.println("9");

if(	val == 8 	)
	System.out.println("8");

if(	val == 7 	)
	System.out.println("7");
if(	val == 6)
	System.out.println("6");
if(	val == 5	)
	System.out.println("5");
if(	val == 4 	)
	System.out.println("4");
if(	val == 3 	)
	System.out.println("3");
if(	val == 2 	)
	System.out.println("2");



}}