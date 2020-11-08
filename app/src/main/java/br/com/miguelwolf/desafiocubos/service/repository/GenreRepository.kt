package br.com.miguelwolf.desafiocubos.service.repository

import android.content.Context
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import br.com.miguelwolf.desafiocubos.service.listener.APIListener
import br.com.miguelwolf.desafiocubos.service.model.GenreModel
import br.com.miguelwolf.desafiocubos.service.model.GenresModel
import br.com.miguelwolf.desafiocubos.service.repository.remote.GenreService
import br.com.miguelwolf.desafiocubos.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreRepository(context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(GenreService::class.java)

    fun all(listener: APIListener<List<GenreModel>>) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailure(mContext.getString(R.string.no_connection))
            return
        }

        val call: Call<GenresModel> = mRemote.all(MovieConstants.API.API_KEY_VALUE, MovieConstants.API.LANGUAGE_VALUE)
        call.enqueue(object : Callback<GenresModel> {
            override fun onFailure(call: Call<GenresModel>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<GenresModel>,
                response: Response<GenresModel>
            ) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let { listener.onSuccess(it.genres, response.code()) }
                }
            }

        })
    }

}