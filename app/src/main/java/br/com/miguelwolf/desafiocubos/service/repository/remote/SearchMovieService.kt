package br.com.miguelwolf.desafiocubos.service.repository.remote

import br.com.miguelwolf.desafiocubos.service.model.MoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMovieService {

    @GET("search/movie")
    fun find(@Query("api_key") apiKey: String,
                   @Query("language") language: String = "pt-BR",
                   @Query("query") query: String): Call<MoviesModel>

}