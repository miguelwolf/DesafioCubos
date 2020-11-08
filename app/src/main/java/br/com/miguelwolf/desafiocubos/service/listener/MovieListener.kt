package br.com.miguelwolf.desafiocubos.service.listener

import br.com.miguelwolf.desafiocubos.service.model.MovieModel

interface MovieListener {

    fun onListClick(movie: MovieModel)

}