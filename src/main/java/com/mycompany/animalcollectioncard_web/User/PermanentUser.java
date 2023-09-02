package com.mycompany.animalcollectioncard_web.User;

public class PermanentUser extends User {

    private boolean admin;

    public PermanentUser() {

    }

    public PermanentUser(String name, String password) {
        this(0, name, password, "", "", "");
    }

    public PermanentUser(int id, String name, String password, String firstName, String lastName, String email) {
        super(id, name, password, firstName, lastName, email);
    }

    public PermanentUser(int id, String name, String password, String firstName, String lastName, String email, boolean admin) {
        super(id, name, password, firstName, lastName, email);
        setAdmin(admin);
    }
    
    public PermanentUser(User user, boolean admin){
        super(user.getId(), user.getName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
        setAdmin(admin);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        if(admin != false && admin != true){
            throw new RuntimeException("Campo admin inválido");
        }
        this.admin = admin;
    }
}
