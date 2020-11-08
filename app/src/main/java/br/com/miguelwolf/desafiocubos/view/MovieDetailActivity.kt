package br.com.miguelwolf.desafiocubos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val photoURL = intent.getStringExtra(PHOTO_URL)
        val title = intent.getStringExtra(TITLE)
        val description = intent.getStringExtra(DESCRIPTION)


        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val path = MovieConstants.API.IMAGE_URL+ "w500/" + photoURL

        Picasso.get().load(path).into(movie_detail_iv);
        movie_detail_tv_title.text = title
        movie_detail_tv_description.text = description

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        val PHOTO_URL = "photo_url"
        val TITLE = "title"
        val DESCRIPTION = "description"
    }

}

