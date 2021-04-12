package br.com.everis.sovamu.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.everis.sovamu.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData() {
        mainViewModel.getUserData()

        mainViewModel.actionView.observe(this, { state ->
            when (state) {
                is MainViewAction.Success -> {
                    Log.d(TAG, "Name: ${state.data.name}, Email: ${state.data.email}")
                }
                is MainViewAction.Loading -> {

                }
                is MainViewAction.Error -> {

                }
            }
        })
    }
}