package poobanco;


public class cliente {

    private int numeroCuenta;
    public float saldo;
    private char tipo;

    public cliente() {
        numeroCuenta = 0;
        saldo = 0.0f;
        tipo = 'i';

    }

    public cliente(int n, float s, char t) {
        numeroCuenta = n;
        saldo = s;
        tipo = t;

    }

    public cliente(int num) {
        numeroCuenta = num;
        saldo = 0;
        tipo = 't';

    }

    public void SetNumeroCuenta(int n) {
        numeroCuenta = n;

    }

    public int getNumeroCuenta() {
        return numeroCuenta;

    }

    public void SetSaldo(int s) {
        saldo = s;

    }

    public float getSaldo() {
        return saldo;

    }

    public void SetTipo(char t) {
        tipo = t;

    }

    public char getTipo() {
        return tipo;

    }

    public String toS() {
        String respuesta = "Numero de Cuenta : " + numeroCuenta + " Saldo : " + saldo + " Tipo: " + tipo;

      return respuesta;

    }

}
