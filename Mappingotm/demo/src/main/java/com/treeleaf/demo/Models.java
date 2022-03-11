package com.treeleaf.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Models {

    @Id
    private int model_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufactures ob;

    public Models(int model_id, String name, Manufactures ob) {
        this.model_id = model_id;
        this.name = name;
        this.ob = ob;
    }

    Models(){

    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufactures getOb() {
        return ob;
    }

    public void setOb(Manufactures ob) {
        this.ob = ob;
    }
}