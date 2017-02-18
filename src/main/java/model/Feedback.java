package model;


public class Feedback {
    private String jurado;
    private String desc;
    private String fecha;

    public Feedback() {
    }

    public Feedback(String jurado, String desc, String fecha) {
        this.jurado = jurado;
        this.desc = desc;
        this.fecha = fecha;
    }

    public String getJurado() {
        return jurado;
    }

    public void setJurado(String jurado) {
        this.jurado = jurado;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
