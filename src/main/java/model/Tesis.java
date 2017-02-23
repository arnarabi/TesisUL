package model;

import java.util.List;

public class Tesis {
    private int id;
    private String titulo;
    private int rating;
    private List<Feedback> feedback;

    public Tesis() {
    }

    public Tesis(int id, String titulo, int rating, List<Feedback> feedback) {
        this.id = id;
        this.titulo = titulo;
        this.rating = rating;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }
}
