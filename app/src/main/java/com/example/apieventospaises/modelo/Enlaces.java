package com.example.apieventospaises.modelo;

public class Enlaces {
    private String previous = null;
    private String current;
    private String next;


    // Getter Methods

    public String getPrevious() {
        return previous;
    }

    public String getCurrent() {
        return current;
    }

    public String getNext() {
        return next;
    }

    // Setter Methods

    public void setPrevious( String previous ) {
        this.previous = previous;
    }

    public void setCurrent( String current ) {
        this.current = current;
    }

    public void setNext( String next ) {
        this.next = next;
    }
}
