package br.com.everis.sovamu.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.databinding.FragmentDailyBinding

class DailyFragment : Fragment() {

    private lateinit var binding: FragmentDailyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }
}