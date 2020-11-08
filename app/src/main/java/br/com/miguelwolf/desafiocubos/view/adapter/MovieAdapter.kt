package br.com.miguelwolf.desafiocubos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.listener.MovieListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.view.viewholder.MovieViewHolder

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var mList: List<MovieModel> = arrayListOf()
    private lateinit var mListener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list, parent, false)
        return MovieViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: MovieListener) {
        mListener = listener
    }

    fun updateList(list: List<MovieModel>) {
        mList = list
        notifyDataSetChanged()
    }

}