package ClasesUtiles;

public class Gestor{
    private  String userName;
    private boolean estaBloqueado;

    public Gestor(String userName, boolean estaBloqueado) {
        this.userName = userName;
        this.estaBloqueado = estaBloqueado;
    }

    public boolean isEstaBloqueado() {
        return estaBloqueado;
    }

    public void setEstaBloqueado(boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
    }

    public String getUserName() {
        return userName;
    }
}