package ClasesUtiles;

public class Inversor {
    private String userName;
    private boolean bloqueado;
    private double saldo;

    public Inversor(String userName, boolean bloqueado, double saldo) {
        this.userName = userName;
        this.bloqueado = bloqueado;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }
}
