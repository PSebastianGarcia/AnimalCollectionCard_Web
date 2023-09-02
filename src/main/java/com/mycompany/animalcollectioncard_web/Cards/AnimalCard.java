package com.mycompany.animalcollectioncard_web.Cards;

public class AnimalCard {
    
    private int id;
    private Animal animal;
//    private boolean favorite;

    public AnimalCard(Animal animal){
        this(0, animal);
    }
    
//    public AnimalCard(int id, Animal animal) {
//        this(id, animal, false);
//    }
    
    public AnimalCard(int id, Animal animal) {
        setId(id);
        this.animal = animal;
    }
    
//    public AnimalCard(int id, Animal animal, boolean favorite){
//        setId(id);
//        this.animal = animal;
//        setFavorite(favorite);        
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para una ID inválido");
        }
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

//    public boolean isFavorite() {
//        return favorite;
//    }
//
//    public void setFavorite(boolean favorite) {
//        this.favorite = favorite;
//    }
}
