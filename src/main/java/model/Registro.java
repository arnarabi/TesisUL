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
    

    public Registro() {
    }

    public Registro(String diahora, String descripcion) {
        this.diahora = diahora;
        this.descripcion = descripcion;
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
    
    
    
    
    
    
    
    }

   
    

   


