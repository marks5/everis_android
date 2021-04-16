package br.com.everis.sovamu.feature.home.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.everis.sovamu.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager2, navigationView: BottomNavigationView) {
    view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            view.adapter?.notifyDataSetChanged()
            navigationView.menu.getItem(position).isChecked = true
        }
    })

    navigationView.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.daily -> view.currentItem = 0
            R.id.scrum -> view.currentItem = 1
            R.id.block -> view.currentItem = 2
        }
        true
    }
}
