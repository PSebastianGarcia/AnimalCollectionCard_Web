package com.mycompany.animalcollectioncard_web.User;

public class TemporalUser extends User{
    
    private String token;
    
    public TemporalUser(User user, String token){
        super(user.getId(), user.getName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
        setToken(token);
    }

    public TemporalUser(int id, String name, String password, String firstName, String lastName, String email) {
        this(id, name, password, firstName, lastName, email, null);
    }
    
    public TemporalUser(int id, String name, String password, String firstName, String lastName, String email, String token) {
        super(id, name, password, firstName, lastName, email);
        setToken(token);
    }
    
    public TemporalUser(){
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
