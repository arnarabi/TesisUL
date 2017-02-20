
package model;

public class RepositorioTesis {

    private int id;
    private String titulo;
    private String descripción;
    private String estado;
    private String autor;


    public RepositorioTesis() {
    }

    public RepositorioTesis(int id, String titulo, String descripción, String estado, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.descripción = descripción;
        this.estado = estado;
        this.autor = autor;
    }

  

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
