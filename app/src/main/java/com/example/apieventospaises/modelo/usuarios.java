package com.example.apieventospaises.modelo;

import java.util.ArrayList;

public class usuarios {
    Meta MetaObject;
    ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    // Getter Methods

    public Meta getMeta() {
        return MetaObject;
    }

    // Setter Methods

    public void setMeta( Meta metaObject ) {
        this.MetaObject = metaObject;
    }

}
