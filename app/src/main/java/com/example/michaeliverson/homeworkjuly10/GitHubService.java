package com.example.michaeliverson.homeworkjuly10;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by michaeliverson on 7/10/17.
 */

public interface GitHubService {

    @GET("users/{user}/repos")
    Call <List<GithubRepo>> callProfle(@Path("user") String user);
}
