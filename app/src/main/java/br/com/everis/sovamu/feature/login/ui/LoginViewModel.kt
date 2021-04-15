package br.com.everis.sovamu.feature.login.ui

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.usecase.LoginUseCase
import br.com.everis.sovamu.ui.BaseViewModel
import kotlinx.coroutines.*

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

    private val _email by lazy { MutableLiveData<String>() }
    val email: LiveData<String>
        get() = _email

    private val _senha by lazy { MutableLiveData<String>() }
    val senha: LiveData<String>
        get() = _senha

    private val _msgError by lazy { MutableLiveData<String>() }
    val msgError: LiveData<String>
        get() = _msgError

    private val _isValid by lazy { MediatorLiveData<Boolean>() }
    val isValid: MediatorLiveData<Boolean>
        get() = _isValid

    init {
        _msgError.value = ""
        _email.value = ""
        _senha.value = ""
        _isValid.value = false
        _isValid.addSource(_email) { isFormValid() }
        _isValid.addSource(_senha) { isFormValid() }
    }

    private val _actionView by lazy { MutableLiveData<LoginViewAction>() }
    val actionView: LiveData<LoginViewAction>
        get() = _actionView

    fun getUserData() {
        _actionView.value = LoginViewAction.Loading(true)
        mUiScope.launch {
            execute()
        }
    }

    private fun clearError() {
        _msgError.value = ""
    }

    private suspend fun execute() {
        mIoScope.async {
            return@async useCase.execute()
        }.await().fold(::showError, ::showSuccess)
    }

    private fun showError(message: String) {
        mUiScope.launch {
            _actionView.postValue(LoginViewAction.Loading(false))
        }
        _actionView.postValue(LoginViewAction.Error(message))
        _msgError.postValue(message)
    }

    private fun showSuccess(data: UserData) {
        _actionView.postValue(LoginViewAction.Loading(false))
        _actionView.postValue(LoginViewAction.Success(data))
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(pass: String) {
        _senha.value = pass
    }

    fun setMsgError(error: String){
        _msgError.value = error
    }

    private fun isFormValid() {
        clearError()
        _isValid.value = PatternsCompat.EMAIL_ADDRESS.matcher(email.value.toString())
            .matches() && senha.value.toString().isNotEmpty()
    }
}