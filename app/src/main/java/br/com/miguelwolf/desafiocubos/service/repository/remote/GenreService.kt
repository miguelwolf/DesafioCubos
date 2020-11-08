package br.com.miguelwolf.desafiocubos.service.repository.remote

import br.com.miguelwolf.desafiocubos.service.model.GenresModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET("genre/movie/list")
    fun all(@Query("api_key") apiKey: String,
            @Query("language") language: String = "pt-BR"): Call<GenresModel>

}