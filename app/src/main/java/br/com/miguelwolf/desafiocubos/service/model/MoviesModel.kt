package br.com.miguelwolf.desafiocubos.service.model

import com.google.gson.annotations.SerializedName

class MoviesModel{

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    lateinit var results: List<MovieModel>

}

class MovieModel {

    @SerializedName("popularity")
    var popularity: Double = 0.0

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("poster_path")
    var posterPath: String = ""

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("backdrop_path")
    lateinit var backdropPath: String

    @SerializedName("original_language")
    lateinit var originaLanguage: String

    @SerializedName("original_title")
    lateinit var originalTitle: String

    @SerializedName("genre_ids")
    lateinit var genreIds: List<Int>

    @SerializedName("title")
    var title: String = ""

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0

    @SerializedName("overview")
    lateinit var overview: String

    @SerializedName("release_date")
    lateinit var releaseDate: String

}