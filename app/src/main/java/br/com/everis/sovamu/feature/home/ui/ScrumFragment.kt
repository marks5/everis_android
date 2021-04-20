package br.com.everis.sovamu.feature.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.R
import br.com.everis.sovamu.databinding.FragmentScrumBinding
import br.com.everis.sovamu.feature.home.model.mockList
import br.com.everis.sovamu.feature.home.util.OnSwipeTouchListener
import br.com.everis.sovamu.feature.home.util.SwipeListener

class ScrumFragment : Fragment(), SwipeListener, ScrumUI {

    private lateinit var binding: FragmentScrumBinding
    private lateinit var scrumAdapter: ScrumAdapter
    private lateinit var notificationView: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrumBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrumAdapter = ScrumAdapter(mutableListOf())
        binding.rvTask.adapter = scrumAdapter
        scrumAdapter.updateList(mockList)

        notificationView = binding.notification.notificationView
        notificationView.setOnTouchListener(OnSwipeTouchListener(this))
    }

    override fun onSwipeLeft() {
        showAnimation(R.anim.move_right_to_left)
    }

    override fun onSwipeRight() {
        showAnimation(R.anim.move_left_to_right)
    }

    override fun showAnimation(animation: Int) {
        with(notificationView) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            startAnimation(AnimationUtils.loadAnimation(context, animation)
            )
            visibility = View.GONE
        }
    }

}