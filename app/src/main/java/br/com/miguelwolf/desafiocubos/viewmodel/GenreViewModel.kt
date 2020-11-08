package br.com.miguelwolf.desafiocubos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.miguelwolf.desafiocubos.service.listener.APIListener
import br.com.miguelwolf.desafiocubos.service.model.GenreModel
import br.com.miguelwolf.desafiocubos.service.repository.GenreRepository

class GenreViewModel(application: Application) : AndroidViewModel(application) {

    private val mListGenre = MutableLiveData<List<GenreModel>>()
    val listGenres: LiveData<List<GenreModel>> = mListGenre

    // Acesso a dados
    private val mTaskRepository: GenreRepository = GenreRepository(application)

    fun all() {

        val listener = object : APIListener<List<GenreModel>> {
            override fun onSuccess(result: List<GenreModel>, statusCode: Int) {
                mListGenre.value = result
            }

            override fun onFailure(message: String) {
                mListGenre.value = null
            }
        }

        mTaskRepository.all(listener)

    }

}