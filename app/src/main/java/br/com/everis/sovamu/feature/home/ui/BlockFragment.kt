package br.com.everis.sovamu.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.databinding.FragmentBlockBinding

class BlockFragment : Fragment() {

    private lateinit var binding: FragmentBlockBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View {
        binding = FragmentBlockBinding.inflate(inflater, container, false)
        return binding.root
    }
}