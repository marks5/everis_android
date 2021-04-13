package br.com.everis.sovamu.feature.login.ui.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.feature.login.model.LoginUI
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

    private val _loginUi by lazy { MutableLiveData<LoginUI>() }
    val loginUI: LiveData<LoginUI>
        get() = _loginUi

    private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
    val actionView: LiveData<LoginViewAction>
        get() = _actionView

    val valid = MediatorLiveData<Boolean>().apply {
        addSource(_loginUi) {
            value = isFormValid(it)
        }
    }

    fun getUserData() {
        _actionView.value = LoginViewAction.Loading(true)
        mUiScope.launch {
            loginUI.value?.let {
                execute(it.email, it.senha)
            }
        }
    }

    private fun isFormValid(loginUI: LoginUI): Boolean {
        Log.d("MVVM", loginUI.toString())
        return Patterns.EMAIL_ADDRESS.matcher(loginUI.email).matches() && loginUI.senha.isNotEmpty()
    }

    private suspend fun execute(user: String, password: String) {
        mIoScope.async {
            return@async useCase.execute(user, password)
        }.await().fold(::showError, ::showSuccess)
    }

    private fun showError(message: String) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Error(message))
        _loginUi.value?.msgError = message
    }

    private fun showSuccess(data: UserData) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Success(data))
    }
}