package br.com.miguelwolf.desafiocubos.service.repository.remote

import br.com.miguelwolf.desafiocubos.service.model.MoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun allOfGenre(@Query("api_key") apiKey: String,
                   @Query("language") language: String = "pt-BR",
                   @Query("with_genres") withGenre: Int): Call<MoviesModel>

}