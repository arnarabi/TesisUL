package model;

import java.util.List;

/**
 * Created by estef on 11/02/2017.
 */
public class Tesis {
    private String titulo;
    private int rating;
    private List<Feedback> feedback;

    public Tesis() {
    }

    public Tesis(String titulo, int rating, List<Feedback> feedback) {
        this.titulo = titulo;
        this.rating = rating;
        this.feedback = feedback;
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
