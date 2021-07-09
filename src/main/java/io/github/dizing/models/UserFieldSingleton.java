package io.github.dizing.models;

public class UserFieldSingleton {

    private static final UserFieldSingleton INSTANCE = new UserFieldSingleton();

    private Field userField;

    public static UserFieldSingleton getInstance() {
        return  UserFieldSingleton.INSTANCE;
    }
    public Field getUserField(){
        return userField;
    }

    public void setUserField(Field field){
        this.userField = field;
    }
    private UserFieldSingleton(){
    }

}
