package com.duk.lab.android.connection.retrofit.services;


import retrofit2.http.GET;

/**
 * Created by dukwonnam on 2017. 2. 2..
 */

public interface GithubMain {
    @GET("/")
    GithubMainResponse getGithubMain();
}
