package br.com.everis.sovamu.feature.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.everis.sovamu.databinding.ActivityMainBinding
import br.com.everis.sovamu.feature.login.ui.viewmodel.LoginViewAction
import br.com.everis.sovamu.feature.login.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = mainViewModel
        binding.lifecycleOwner = this

        observeData()
    }

    private fun observeData() {
        mainViewModel.actionView.observe(this, { state ->
            when (state) {
                is LoginViewAction.Success -> {
                    Timber.d("$TAG Name: %s, Email: %s", state.data.name, state.data.email)
                }
                is LoginViewAction.Loading -> {
                }
                is LoginViewAction.Error -> {
                }
            }
        })
    }
}