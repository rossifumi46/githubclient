package com.example.githubclient.data.api;

import com.example.githubclient.data.model.project.Project;
import com.example.githubclient.data.model.project.ProjectResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {

    @GET("search/repositories?q=query")
    Single<ProjectResponse> getProjects(@Query("q") String query);

    @GET("repos/{fullname}")
    Single<Project> getProject(@Path(value = "fullname", encoded = true) String fullname);

}
