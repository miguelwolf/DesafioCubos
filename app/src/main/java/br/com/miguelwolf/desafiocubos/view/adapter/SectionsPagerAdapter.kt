package br.com.miguelwolf.desafiocubos.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.miguelwolf.desafiocubos.service.model.GenreModel
import br.com.miguelwolf.desafiocubos.view.PlaceholderFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, val tabs: List<GenreModel>)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, tabs.get(position).id)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs.get(position).name
    }

    override fun getCount(): Int {
        return 4
    }
}