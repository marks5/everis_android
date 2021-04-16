package br.com.everis.sovamu.feature.login.binding

import android.text.Editable
import android.text.TextWatcher
import br.com.everis.sovamu.feature.login.ui.LoginViewModel

class SoVamuTextWatcher(private val viewModel: LoginViewModel, private val identifier: Identifier) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //does nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val text = s.toString()
        when(identifier) {
            Identifier.EMAIL -> viewModel.setEmail(text)
            Identifier.PASSWORD -> viewModel.setPassword(text)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        //does nothing
    }

    enum class Identifier {
        EMAIL,
        PASSWORD
    }
}