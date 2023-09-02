package com.mycompany.animalcollectioncard_web.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Person {

    private int id;
    private String name;
    private String password;
    private String email;

    public User(int id, String name, String password, String firstName, String lastName, String email) {
        super(firstName, lastName);
        setId(id);
        setName(name);
        setPassword(password);
        setEmail(email);
    }

    public User(String name, String password, String firstName, String lastName, String email) {
        this(0, name, password, firstName, lastName, email);
    }

    public User(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public User() {
        super(null, null);
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
        this.name = name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
            throw new RuntimeException("Campo password inválido");
        }
        this.password = password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        String regEx = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(mail);

        if (matcher.matches()) {
            this.email = mail;
        } else {
            System.out.println("La dirección de correo electrónico no es válida.");
        }
    }
}
