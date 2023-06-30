/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animalcollectioncard_web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author seba3
 */
public class Model {
//    private ArrayList<Animal> animals;

//    public Model() {
//        animals = new ArrayList<>();
//        addAnimal();
//    }
    
    public Model() {
    }

//    private void addAnimal() {
//        animals.add(new Animal("Elefante", "Loxodonta", "Africana", "elephant.png"));
//        animals.add(new Animal("Cocodrilo", "Crocodylus", "Intermedius", "crocodile.png"));
//        animals.add(new Animal("Tiburón", "Ginglymostoma", "cirratum", "shark.png"));
//    }
    
//    public ArrayList<Animal> getAnimals() {
//        return animals;
//    }
    
    public ArrayList<Animal> getAnimals() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM animals");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
           int id = rs.getInt(1);
           String name = rs.getString(2);
           String genus = rs.getString(3);
           String species = rs.getString(4);
           String foto = rs.getString(5);
           
           animals.add(new Animal(name, genus, species, foto));
        }
        return animals;
    }
}
