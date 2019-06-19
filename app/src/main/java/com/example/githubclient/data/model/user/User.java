package com.example.githubclient.data.model.user;

import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("email")
    private String email;


    public User(String login, String name, String followers,
                String following, String avatar, String email){
        this.login=login;
        this.name=name;
        this.followers=followers;
        this.following=following;
        this.avatar=avatar;
        this.email=email;

    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
