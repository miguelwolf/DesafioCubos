package br.com.miguelwolf.desafiocubos.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenresModel {

    @SerializedName("genres")
    @Expose
    lateinit var genres: List<GenreModel>

}

class GenreModel {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    lateinit var name: String

}