package br.com.miguelwolf.desafiocubos.service.repository

import android.content.Context
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import br.com.miguelwolf.desafiocubos.service.listener.APIListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.service.model.MoviesModel
import br.com.miguelwolf.desafiocubos.service.repository.remote.MovieService
import br.com.miguelwolf.desafiocubos.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository(context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(MovieService::class.java)

    fun all(listener: APIListener<List<MovieModel>>, genreId: Int) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.no_connection))
            return
        }

        val call: Call<MoviesModel> = mRemote.allOfGenre(MovieConstants.API.API_KEY_VALUE,
        MovieConstants.API.LANGUAGE_VALUE,
        genreId)
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