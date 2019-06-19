package com.example.githubclient.data.model.project;


import com.example.githubclient.data.model.user.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Project implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("owner")
    private User user;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @SerializedName("full_name")
    private String fullname;

    public Project(String language, String description, String name, String fullname) {
        this.setLanguage(language);
        this.setDescription(description);
        this.setName(name);
        this.setFullname(fullname);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFullname() {
        return fullname;
    }

    public User getOwner() {
        return user;
    }
}
