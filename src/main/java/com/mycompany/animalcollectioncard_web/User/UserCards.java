package com.mycompany.animalcollectioncard_web.User;

import java.util.ArrayList;

public class UserCards {

    private int userId;
    private ArrayList<Integer> cardIds;

    public UserCards(int userId) {
        setUserId(userId);
        this.cardIds = new ArrayList<Integer>();
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para una ID inválido");
        }
        this.userId = id;
    }

    public ArrayList<Integer> getCardIds() {        
        return this.cardIds;
    }

    public void addCardIds(int id){
        this.cardIds.add(id);
    }
}
