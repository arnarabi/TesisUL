
package model;

public class RepositorioTesis {

    private int id;
    private String fecha;
    private String titulo;
    private String autor;


    public RepositorioTesis() {
    }

    public RepositorioTesis(int id, String fecha, String titulo, String autor) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

   


}
