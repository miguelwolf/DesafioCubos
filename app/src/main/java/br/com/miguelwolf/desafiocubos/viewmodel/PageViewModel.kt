package br.com.miguelwolf.desafiocubos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.miguelwolf.desafiocubos.service.listener.APIListener
import br.com.miguelwolf.desafiocubos.service.listener.ValidationListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.service.repository.MovieRepository
import br.com.miguelwolf.desafiocubos.service.repository.SearchMovieRepository

class PageViewModel(application: Application) : AndroidViewModel(application) {

    private val _index = MutableLiveData<Int>()

    private val mValidation = MutableLiveData<ValidationListener>()
    val validation: LiveData<ValidationListener> = mValidation

    private val mMovieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = mMovieList

    // Acesso a dados
    private val mMovieRepository: MovieRepository = MovieRepository(application)
    private val mSearchRepository: SearchMovieRepository = SearchMovieRepository(application)


    fun list(genreId: Int) {
        val listener = object : APIListener<List<MovieModel>> {
            override fun onSuccess(result: List<MovieModel>, statusCode: Int) {
                mMovieList.value = result
            }

            override fun onFailure(message: String) {
                mMovieList.value = null
                mValidation.value = ValidationListener(message)
            }
        }

        mMovieRepository.all(listener, genreId)
    }

    fun search(s: String) {

        val listener = object : APIListener<List<MovieModel>> {
            override fun onSuccess(result: List<MovieModel>, statusCode: Int) {
                mMovieList.value = result
            }

            override fun onFailure(message: String) {
                mMovieList.value = null
                mValidation.value = ValidationListener(message)
            }

        }

        mSearchRepository.find(listener, s)

    }

    fun setIndex(index: Int) {
        _index.value = index
    }


}