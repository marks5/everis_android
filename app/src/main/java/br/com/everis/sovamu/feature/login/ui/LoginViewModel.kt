package br.com.everis.sovamu.feature.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.usecase.LoginUseCase
import br.com.everis.sovamu.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class LoginViewAction {
    open class Success(val data: UserData) : LoginViewAction()
    open class Loading(val loading: Boolean) : LoginViewAction()
    open class Error(val message: String) : LoginViewAction()
}

class LoginViewModel(
        val useCase: LoginUseCase,
        mainDispatcher: CoroutineDispatcher,
        ioDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher, ioDispatcher) {

    private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
    val actionView: LiveData<LoginViewAction>
        get() = _actionView

    fun getUserData() {
        _actionView.value = LoginViewAction.Loading(true)
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
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Error(message))
    }

    private fun showSuccess(data: UserData) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Success(data))
    }
}