package br.com.miguelwolf.desafiocubos.service.constants

class MovieConstants private constructor() {


    object HTTP {
        const val SUCCESS = 200
    }

    object API {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val API_KEY_VALUE = "9ff955a40966431f54289b8d38a85f38"
        const val LANGUAGE_VALUE = "pt-BR"
    }

}