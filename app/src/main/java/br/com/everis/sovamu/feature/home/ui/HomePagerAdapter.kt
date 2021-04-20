package br.com.everis.sovamu.feature.home.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScrumFragment()
            1 -> ScrumFragment()
            else -> ScrumFragment()
        }
    }

    override fun getItemCount(): Int = 3
}