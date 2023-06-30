/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalcollectioncard_web;

import java.io.Serializable;

/**
 *
 * @author seba3
 */
public class Animal implements Serializable{
     private String name;
     private String genus;
     private String species;
     private String foto;

    public Animal() {
    }
     
    public Animal(String name, String genus, String species, String foto) {
        this.name = name;
        this.genus = genus;
        this.species = species;
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
     
}
