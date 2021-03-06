package br.com.miguelwolf.desafiocubos.service.repository

import android.content.Context
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import br.com.miguelwolf.desafiocubos.service.listener.APIListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.service.model.MoviesModel
import br.com.miguelwolf.desafiocubos.service.repository.remote.RetrofitClient
import br.com.miguelwolf.desafiocubos.service.repository.remote.SearchMovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchMovieRepository(context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(SearchMovieService::class.java)

    fun find(listener: APIListener<List<MovieModel>>, search: String) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.no_connection))
            return
        }

        val call:Call<MoviesModel> = mRemote.find(MovieConstants.API.API_KEY_VALUE, MovieConstants.API.LANGUAGE_VALUE, search)

        call.enqueue(object : Callback<MoviesModel> {
            override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<MoviesModel>,
                response: Response<MoviesModel>
            ) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let { listener.onSuccess(it.results, response.code()) }
                }
            }

        })
    }

}