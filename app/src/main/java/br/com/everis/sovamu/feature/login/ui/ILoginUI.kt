package br.com.everis.sovamu.feature.login.ui

import br.com.everis.sovamu.feature.login.model.LoginUI

interface ILoginUI {
    fun observeData(data: LoginUI)
    fun showLoading(loading: Boolean)
    fun showError(msgError: String)
}