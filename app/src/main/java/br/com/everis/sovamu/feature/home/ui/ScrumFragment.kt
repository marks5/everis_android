package br.com.everis.sovamu.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.databinding.FragmentScrumBinding
import br.com.everis.sovamu.feature.home.model.mockList

class ScrumFragment : Fragment() {

    private lateinit var binding: FragmentScrumBinding
    private lateinit var scrumAdapter: ScrumAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrumAdapter = ScrumAdapter(mutableListOf())
        binding.rvTask.adapter = scrumAdapter
        scrumAdapter.updateList(mockList)
    }
}