package br.com.everis.sovamu.feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.feature.home.model.MockNotification
import br.com.everis.sovamu.feature.home.model.mockListNotification
import br.com.everis.sovamu.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

private const val INITIAL_INDEX = 0

sealed class ScrumViewAction {
    open class ShowNotification(val notification: MockNotification) : ScrumViewAction()
    open class StartAnimationNotification(
            val notification: MockNotification,
            val showCardAnimation: Int,
            val hideCardAnimation: Int
    ) : ScrumViewAction()

    open class EndAnimationNotification(val hideCardAnimation: Int) : ScrumViewAction()
    open class Loading(val loading: Boolean) : ScrumViewAction()
}

class ScrumViewModel(
        private val list: List<MockNotification> = mockListNotification,
        mainDispatcher: CoroutineDispatcher,
        ioDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher, ioDispatcher) {

    private val _scrumView by lazy { MutableLiveData<ScrumViewAction>() }
    val scrumView: LiveData<ScrumViewAction>
        get() = _scrumView

    private var index = INITIAL_INDEX

    fun getNotification() {
        _scrumView.postValue(ScrumViewAction.Loading(true))

        if (list.isNotEmpty() && index < list.size) {
            _scrumView.postValue(ScrumViewAction.Loading(false))
            _scrumView.value = ScrumViewAction.ShowNotification(list[index])
            index++
        } else _scrumView.postValue(ScrumViewAction.Loading(false))
    }

    fun actionNotification(showCardAnimation: Int, hideCardAnimation: Int) {
        _scrumView.value = ScrumViewAction.Loading(true)

        when {
            (index < list.size) -> {
                _scrumView.value = ScrumViewAction.StartAnimationNotification(
                        list[index],
                        showCardAnimation,
                        hideCardAnimation
                )
                index++
                _scrumView.value = ScrumViewAction.Loading(false)
            }

            (index == list.size) -> {
                _scrumView.value = ScrumViewAction.EndAnimationNotification(hideCardAnimation)
                _scrumView.value = ScrumViewAction.Loading(false)
            }
        }
    }
}
