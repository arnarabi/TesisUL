package model;

/**
 * Created by estef on 11/02/2017.
 */
public class Asesor {
    private int id;
    private String nombre;
    private String dia;
    private String hora;

    public Asesor() {
    }

    public Asesor(int id, String nombre, String dia, String hora) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
