package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


// Representation of a User in the system
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private Type type; // if he is the System Admin / Local Admin / Technician
    private String password;
    private String serverIdForAdmin;

    public User(String name, String email, Type type, String password) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public User(String name, String email, Type type, String password, String serverIdForAdmin) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
        this.serverIdForAdmin = serverIdForAdmin;
    }

    public String getServerIdForAdmin() {
        return serverIdForAdmin;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }
}
