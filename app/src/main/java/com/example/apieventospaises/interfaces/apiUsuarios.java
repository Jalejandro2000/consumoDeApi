package com.example.apieventospaises.interfaces;

import com.example.apieventospaises.modelo.usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiUsuarios {
    @GET("public/v1/users?page=1")
    Call<List<usuarios>> getusuarios();
}
