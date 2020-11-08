package br.com.miguelwolf.desafiocubos.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.service.listener.MovieListener
import br.com.miguelwolf.desafiocubos.service.model.MovieModel
import br.com.miguelwolf.desafiocubos.view.adapter.MovieAdapter
import br.com.miguelwolf.desafiocubos.viewmodel.PageViewModel


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private var mGenreId: Int = 0
    private var mQueryBackup: String = ""

    private val mAdapter = MovieAdapter()

    private lateinit var recycler: RecyclerView

    private lateinit var mListener: MovieListener
    private lateinit var mPageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        mPageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        // Filtro de tarefas
        mGenreId = arguments!!.getInt(ARG_GENRE_CODE, 0)

        recycler = root.findViewById(R.id.section_rv)
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = mAdapter

        mListener = object: MovieListener {

            override fun onListClick(movie: MovieModel) {

                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.PHOTO_URL, movie.posterPath)
                intent.putExtra(MovieDetailActivity.TITLE, movie.title)
                intent.putExtra(MovieDetailActivity.DESCRIPTION, movie.overview)
                startActivity(intent)

            }

        }

        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)

        if (mQueryBackup.isEmpty()) {
            mPageViewModel.list(mGenreId)
        } else {
            mPageViewModel.search(mQueryBackup)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val menuInflater: MenuInflater = inflater
        menuInflater.inflate(R.menu.main_menu, menu)

        val manager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                if (query != null && query.isNotEmpty()) {
                    mQueryBackup = query
                    mPageViewModel.search(query!!)
                } else {
                    mQueryBackup = ""
                    mPageViewModel.list(mGenreId)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null && newText.isNotEmpty()) {
                    mQueryBackup = newText
                    mPageViewModel.search(newText!!)
                } else {
                    mQueryBackup = ""
                    mPageViewModel.list(mGenreId)
                }

                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun observe() {
        mPageViewModel.movieList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mAdapter.updateList(it)
            }
        })

        mPageViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (!it.success()) {
                Toast.makeText(context, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })
    }



    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_GENRE_CODE = "genre_code"
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, genreCode: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_GENRE_CODE, genreCode)
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}