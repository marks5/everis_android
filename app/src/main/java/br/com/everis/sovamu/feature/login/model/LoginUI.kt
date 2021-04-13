package br.com.everis.sovamu.feature.login.model

data class LoginUI(
        val email: String,
        val senha: String,
        var msgError: String
) {
    override fun toString(): String {
        return super.toString()
    }
}