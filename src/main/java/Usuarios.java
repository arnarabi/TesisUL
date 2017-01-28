public class Usuarios {

    private String usuario;
    private String pass;

    public Usuarios() {
    }

    public Usuarios(String nombre, String fecha) {
        this.usuario = nombre;
        this.pass = fecha;
    }

    public String getNombre() {
        return usuario;
    }

    public String getFecha() {
        return pass;
    }

    public void setNombre(String nombre) {
        this.usuario = nombre;
    }

    public void setFecha(String fecha) {
        this.pass = fecha;
    }

}
