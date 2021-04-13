package br.com.everis.sovamu.feature.login.ui

interface ILoginUI {
    fun observeData()
    fun showLoading(loading: Boolean)
    fun showError(msgError: String)
}