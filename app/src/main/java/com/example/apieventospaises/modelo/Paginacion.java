package com.example.apieventospaises.modelo;

public class Paginacion {
    private float total;
    private float pages;
    private float page;
    private float limit;
    Enlaces enlaces;


    // Getter Methods

    public float getTotal() {
        return total;
    }

    public float getPages() {
        return pages;
    }

    public float getPage() {
        return page;
    }

    public float getLimit() {
        return limit;
    }

    public Enlaces getLinks() {
        return enlaces;
    }

    // Setter Methods

    public void setTotal( float total ) {
        this.total = total;
    }

    public void setPages( float pages ) {
        this.pages = pages;
    }

    public void setPage( float page ) {
        this.page = page;
    }

    public void setLimit( float limit ) {
        this.limit = limit;
    }

    public void setLinks( Enlaces linksObject ) {
        this.enlaces = linksObject;
    }
}
