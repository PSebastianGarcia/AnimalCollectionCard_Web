package com.mycompany.animalcollectioncard_web.Cards;

import java.io.Serializable;

public class Animal implements Serializable {

    private int id;
    private String name;
    private String genus;
    private String species;
    private String foto;

    public Animal() {
    }
    
    public Animal(String name, String genus, String species, String foto){
        this(0, name, genus, species, foto);
    }

    public Animal(int id, String name, String genus, String species, String foto) {
        setId(id);
        setName(name);
        setGenus(genus);
        setSpecies(species);
        setFoto(foto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para una ID inválido");
        }
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Campo nombre inválido");
        }
        this.name = name;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        if (genus == null || genus.trim().isEmpty()) {
            throw new RuntimeException("Campo género inválido");
        }
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new RuntimeException("Campo especie inválido");
        }
        this.species = species;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        if (foto == null || foto.trim().isEmpty()) {
            throw new RuntimeException("Campo foto inválido");
        }
        this.foto = foto;
    }

}
