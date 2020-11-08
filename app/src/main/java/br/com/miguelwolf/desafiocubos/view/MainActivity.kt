package br.com.miguelwolf.desafiocubos.view

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.miguelwolf.desafiocubos.R
import br.com.miguelwolf.desafiocubos.view.adapter.SectionsPagerAdapter
import br.com.miguelwolf.desafiocubos.viewmodel.GenreViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)

        setSupportActionBar(toolbar)

        observe()

    }

    private fun observe() {

        mViewModel.all()

        mViewModel.listGenres.observe(this, Observer {
            if (it != null) {

                val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, it)

                val viewPager: ViewPager = findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter

                val tabs: TabLayout = findViewById(R.id.tabs)
                tabs.setupWithViewPager(viewPager)
            }
        })
    }

}