package br.com.everis.sovamu.feature.updateuser.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.R.*
import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import br.com.everis.sovamu.feature.updateuser.usecase.UpdateUserUseCase
import br.com.everis.sovamu.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class UpdateUserViewAction {
    open class Success(val data: UpdateUser) : UpdateUserViewAction()
    open class Loading(val loading: Boolean) : UpdateUserViewAction()
    open class Error(val message: String) : UpdateUserViewAction()
}

class UpdateUserViewModel(
    val useCase: UpdateUserUseCase,
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher, ioDispatcher) {

    private val _updateUser by lazy { MutableLiveData<UpdateUser>() }
    val updateUser: LiveData<UpdateUser>
        get() = _updateUser

    val zipCode by lazy { MutableLiveData<String>("") }
    val setFocus by lazy { MutableLiveData<String>("") }
    val name by lazy { MutableLiveData<String>("") }
    val surname by lazy { MutableLiveData<String>("") }
    val email by lazy { MutableLiveData<String>("") }
    val password by lazy { MutableLiveData<String>("") }
    val passwordNew by lazy { MutableLiveData<String>("") }
    val confirmation by lazy { MutableLiveData<String>("") }

    private val _messageAlert by lazy { MutableLiveData<String>("") }
    val messageAlert: LiveData<String>
        get() = _messageAlert

    private val _actionView by lazy { MutableLiveData<UpdateUserViewAction>() }
    val actionView: LiveData<UpdateUserViewAction>
        get() = _actionView

    fun putUpdateUser(updateUser: UpdateUser, context: Context) {
        if (validadeUpdateUser(updateUser, context)) {
            _actionView.value = UpdateUserViewAction.Loading(true)
            mUiScope.launch { execute() }
        }
    }

    private suspend fun execute() {
        fun showError(message: String) {
            mIoScope.launch {
                _actionView.postValue(UpdateUserViewAction.Loading(false))
            }
            _actionView.postValue(UpdateUserViewAction.Error(message))
            _messageAlert.postValue("")
        }

        fun showSuccess(data: UpdateUser) {
            _actionView.postValue(UpdateUserViewAction.Loading(false))
            _actionView.postValue(UpdateUserViewAction.Success(data))
        }

        mIoScope.async {
            return@async useCase.execute()
        }.await().fold(::showError, ::showSuccess)
    }

    private fun validadeUpdateUser(updateUser: UpdateUser, context: Context): Boolean {
        updateUser.apply {
            if (name.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (surname.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (email.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (password.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (cep.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (logradouro.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (complemento.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (bairro.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            } else if (localidade.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            }
            if (uf.isEmpty()) {
                _messageAlert.value = context.getString(string.preencher_campos)
                return false
            }
        }
        return true
    }

    private val _zipCodeAction by lazy { MutableLiveData<UpdateUserViewAction>() }
    val zipCodeAction: LiveData<UpdateUserViewAction>
        get() = _zipCodeAction

    fun getBuscarCep(context: Context) {
        if (validateCepInput(zipCode.value.toString(),context)) {
            _zipCodeAction.value = UpdateUserViewAction.Loading(true)
            mUiScope.launch { executeEndereco(zipCode.value.toString()) }
        }
    }

    private suspend fun executeEndereco(cep: String) {
        fun showCepError(message: String) {
            mIoScope.launch {
                _zipCodeAction.postValue(UpdateUserViewAction.Loading(false))
            }
            _zipCodeAction.postValue(UpdateUserViewAction.Error(message))
            _messageAlert.postValue(message)
        }

        fun showCepSuccess(data: UpdateUser) {
            _zipCodeAction.postValue(UpdateUserViewAction.Loading(false))
            _zipCodeAction.postValue(UpdateUserViewAction.Success(data))
            _updateUser.postValue(data)
        }

        mIoScope.async {
            return@async useCase.executeEnderecoUser(cep)
        }.await().fold(::showCepError, ::showCepSuccess)
    }

    private fun validateCepInput(cep: String, context: Context): Boolean {
        if (cep.isEmpty()) {
            _messageAlert.value = context.getString(string.preencher_campos)
            return false
        }
        return true
    }



}