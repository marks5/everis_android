package br.com.everis.sovamu.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.databinding.FragmentScrumBinding
import br.com.everis.sovamu.feature.home.model.mockList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScrumFragment : Fragment() {

    private lateinit var binding: FragmentScrumBinding
    private lateinit var scrumAdapter: ScrumAdapter
    private val viewModel by viewModel<ScrumViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrumBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrumAdapter = ScrumAdapter(mutableListOf())
        binding.rvTask.adapter = scrumAdapter
        scrumAdapter.updateList(mockList)

        binding.tvDate.setOnClickListener {
            viewModel.addDay()
        }
    }
}