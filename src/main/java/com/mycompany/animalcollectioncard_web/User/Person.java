package com.mycompany.animalcollectioncard_web.User;

public class Person {
    
    private String firstName;
    private String lastName;
    // atributo para fecha de nacimiento

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RuntimeException("Campo nombre inválido");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RuntimeException("Campo apellido inválido");
        }
        this.lastName = lastName.trim();
    }    
}
