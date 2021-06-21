package br.com.everis.sovamu.feature.updateuser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.everis.sovamu.databinding.FragmentUpdateUserBinding
import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val TAG = "UpdateUserFragment"

class UpdateUserFragment : Fragment() {

    private val updateUserViewModel by viewModel<UpdateUserViewModel>()
    private lateinit var binding: FragmentUpdateUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = updateUserViewModel
        binding.lifecycleOwner = this

        submitUpdateUser()
    }

    private fun submitUpdateUser() {
        binding.fabSubmit.setOnClickListener {
            val updateUser = UpdateUser(
                binding.edtNome.text.toString(),
                binding.edtApelido.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtSenha.text.toString(),
                binding.edtNovaSenha.text.toString(),
                binding.edtConfirmacao.text.toString(),
                binding.edtCep.text.toString(),
                binding.edtLogradouro.text.toString(),
                binding.edtCompl.text.toString(),
                binding.edtBairro.text.toString(),
                binding.edtLocalidade.text.toString(),
                binding.edtUF.text.toString()
            )
            updateUserViewModel.putUpdateUser(updateUser)
            observeUpdateUser()
        }
    }

    private fun observeUpdateUser() {
        updateUserViewModel.actionView.observe(viewLifecycleOwner, { state ->
            when (state) {
                is UpdateUserViewAction.Success -> {

                }
                is UpdateUserViewAction.Loading -> {
                    Timber.tag(TAG).d("${state.loading}")
                }
                is UpdateUserViewAction.Error -> {
                    Timber.tag(TAG).d("${state.message}")
                }
            }
        })
    }


}