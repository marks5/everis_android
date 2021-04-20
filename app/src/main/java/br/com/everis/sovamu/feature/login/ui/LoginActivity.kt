package br.com.everis.sovamu.feature.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.everis.sovamu.R
import br.com.everis.sovamu.databinding.ActivityMainBinding
import br.com.everis.sovamu.feature.home.ui.HomeActivity
import br.com.everis.sovamu.feature.login.binding.SoVamuTextWatcher
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
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        observeData()

        binding.edtEmail.addTextChangedListener(
            SoVamuTextWatcher(
                viewModel = mainViewModel,
                SoVamuTextWatcher.Identifier.EMAIL
            )
        )
        binding.edtPassword.addTextChangedListener(
            SoVamuTextWatcher(
                viewModel = mainViewModel,
                SoVamuTextWatcher.Identifier.PASSWORD
            )
        )
    }

    private fun observeData() {
        mainViewModel.actionView.observe(this, { state ->
            when (state) {
                is LoginViewAction.Success -> {
                    Timber.tag(TAG).d("Name: ${state.data.name}, Email: ${state.data.email}")
                    startHome()
                }
                is LoginViewAction.Loading -> {
                    Timber.tag(TAG).d("${state.loading}")
                    enableBtnEntrar(state.loading)
                }
                is LoginViewAction.Error -> {
                }
            }
        })
    }

    private fun startHome() {
        startActivity(
            Intent(this, HomeActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
        )
    }

    private fun enableBtnEntrar(loading: Boolean) {
        binding.btnEntrar.isEnabled = !loading
        if(loading) {
            binding.btnEntrar.text = getString(R.string.txt_carregando)
        } else {
            binding.btnEntrar.text = getString(R.string.btn_entrar)
        }
    }
}