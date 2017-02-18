package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import utils.ViewUtil;


public class Registro {
    private int id;
    private String diahora;
    private String descripcion;
    private boolean aprProfesor;
    private boolean aprAlumno;

    public Registro() {
    }

    public Registro(int id, String diahora, String descripcion, boolean aprProfesor, boolean aprAlumno) {
        this.id = id;
        this.diahora = diahora;
        this.descripcion = descripcion;
        this.aprProfesor = aprProfesor;
        this.aprAlumno = aprAlumno;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiahora() {
        return diahora;
    }

    public void setDiahora(String diahora) {
        this.diahora = diahora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isAprProfesor() {
        return aprProfesor;
    }

    public void setAprProfesor(boolean aprProfesor) {
        this.aprProfesor = aprProfesor;
    }

    public boolean isAprAlumno() {
        return aprAlumno;
    }

    public void setAprAlumno(boolean aprAlumno) {
        this.aprAlumno = aprAlumno;
    }

   

}
