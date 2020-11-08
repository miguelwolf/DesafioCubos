package br.com.miguelwolf.desafiocubos.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import br.com.miguelwolf.desafiocubos.service.listener.MovieListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.service.repository.MovieRepository
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View, val listener: MovieListener) :
    RecyclerView.ViewHolder(itemView) {

    private val mPriorityRepository = MovieRepository(itemView.context)

    private var mCard: CardView = itemView.findViewById(R.id.row_movie_cv)
    private var mTextTitle: TextView = itemView.findViewById(R.id.row_movie_tv)
    private var mImage: ImageView = itemView.findViewById(R.id.row_movie_iv)

    fun bindData(model: MovieModel) {

        this.mTextTitle.text = model.title

        Picasso.get().load(MovieConstants.API.IMAGE_URL + "w200/" + model.posterPath).into(this.mImage);

        // Eventos
        mCard.setOnClickListener {
            listener.onListClick(model)
        }

    }

}