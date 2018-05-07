package poobanco;

public class Principal {

    public static void main(String[] arg) {
        cliente c1 = new cliente();
        cliente c2 = new cliente(200,1000,'i');
        
        System.out.println(c1.toString());
        System.out.println("----");
        System.out.println(c2.toString());

    }
}
