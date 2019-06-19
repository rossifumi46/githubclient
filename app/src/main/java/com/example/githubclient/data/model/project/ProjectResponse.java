package com.example.githubclient.data.model.project;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ProjectResponse implements Serializable {

    @SerializedName("items")
    private List<Project> mProjects;

    public List<Project> getProjects() {
        return mProjects;
    }

    public void setProjects(List<Project> projects) {
        mProjects = projects;
    }
}
