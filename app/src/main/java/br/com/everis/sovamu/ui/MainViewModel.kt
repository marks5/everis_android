package br.com.everis.sovamu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.usecase.MainUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class MainViewAction {
    open class Success(val data: UserData) : MainViewAction()
    open class Loading(val loading: Boolean) : MainViewAction()
    open class Error(val message: String) : MainViewAction()
}

class MainViewModel(
    val useCase: MainUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher, ioDispatcher) {

    private val _actionView by lazy { MutableLiveData<MainViewAction>() }
    val actionView: LiveData<MainViewAction>
        get() = _actionView

    fun getUserData() {
        _actionView.value = MainViewAction.Loading(true)
        mUiScope.launch {
            execute()
        }
    }

    private suspend fun execute() {
        mIoScope.async {
            return@async useCase.execute()
        }.await().fold(::showError, ::showSuccess)
    }

    private fun showError(message: String) {
        _actionView.postValue(MainViewAction.Loading(false))
        _actionView.postValue(MainViewAction.Error(message))
    }

    private fun showSuccess(data: UserData) {
        _actionView.postValue(MainViewAction.Loading(false))
        _actionView.postValue(MainViewAction.Success(data))
    }
}