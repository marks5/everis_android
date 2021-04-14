package br.com.everis.sovamu.feature.login.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.everis.sovamu.databinding.ActivityMainBinding
import br.com.everis.sovamu.feature.login.model.LoginUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity(), ILoginUI {

    private val mainViewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            observeData(binding.login)
        }
    }

    override fun observeData(data: LoginUI?) {
        mainViewModel.actionView.observe(this, { state ->
            when (state) {
                is LoginViewAction.Success -> {
                    Timber.d("Name: ${state.data.name}, Email: ${state.data.email}")
                }
                is LoginViewAction.Loading -> {
                    showLoading(state.loading)
                }
                is LoginViewAction.Error -> {
                    showError(state.message)
                }
            }
        })
    }

    override fun showLoading(loading: Boolean) {

    }

    override fun showError(msgError: String) {
        binding.login?.msgError = msgError
    }
}