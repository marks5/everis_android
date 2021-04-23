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
import br.com.everis.sovamu.feature.home.model.MockNotification
import br.com.everis.sovamu.feature.home.model.mockList
import br.com.everis.sovamu.feature.home.util.OnSwipeTouchListener
import br.com.everis.sovamu.feature.home.util.SwipeListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val TAG = "ScrumFragment"

class ScrumFragment : Fragment(), SwipeListener, ScrumUI {

    private val scrumViewModel by viewModel<ScrumViewModel>()
    private lateinit var binding: FragmentScrumBinding
    private lateinit var scrumAdapter: ScrumAdapter
    private lateinit var notificationView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

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
        scrumViewModel.getNotification()

        notificationView = binding.notification.root
        notificationView.setOnTouchListener(OnSwipeTouchListener(this))
    }

    private fun observeLiveData() {
        scrumViewModel.scrumView.observe(this, { state ->
            when (state) {
                is ScrumViewAction.ShowNotification -> getNotification(state.notification)
                is ScrumViewAction.StartAnimationNotification -> startAnimationNotification(
                        state.notification,
                        state.showCardAnimation,
                        state.hideCardAnimation
                )
                is ScrumViewAction.EndAnimationNotification -> endAnimationNotification(state.hideCardAnimation)
                is ScrumViewAction.Loading -> {
                    Timber.tag(TAG).d("Loading... ${state.loading}" )
                }
            }
        })
    }

    override fun onSwipeLeft() {
        scrumViewModel.actionNotification(R.anim.return_from_left, R.anim.move_right_to_left)
    }

    override fun onSwipeRight() {
        scrumViewModel.actionNotification(R.anim.return_from_right, R.anim.move_left_to_right)
    }

    override fun startAnimationNotification(
            notification: MockNotification,
            showCardAnimation: Int,
            hideCardAnimation: Int
    ) {
        animationCardNotification(hideCardAnimation)
        getNotification(notification)
        animationCardNotification(showCardAnimation)
    }

    override fun endAnimationNotification(hideCardAnimation: Int) {
        animationCardNotification(hideCardAnimation)
        notificationView.visibility = View.GONE
    }

    override fun getNotification(notification: MockNotification) {
        notificationView.visibility = View.VISIBLE

        binding.apply {
            titleNotification = notification.title
            notificationText = notification.message
        }
    }

    override fun animationCardNotification(animation: Int) {
        notificationView.apply {
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
            startAnimation(AnimationUtils.loadAnimation(context, animation))
        }
    }

}