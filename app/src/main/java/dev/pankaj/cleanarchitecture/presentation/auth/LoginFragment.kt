package dev.pankaj.cleanarchitecture.presentation.auth

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pankaj.cleanarchitecture.R
import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.LoginResponse
import dev.pankaj.cleanarchitecture.databinding.FragmentLoginBinding
import dev.pankaj.cleanarchitecture.extensions.disable
import dev.pankaj.cleanarchitecture.extensions.enable
import dev.pankaj.cleanarchitecture.extensions.hide
import dev.pankaj.cleanarchitecture.extensions.show
import dev.pankaj.cleanarchitecture.presentation.auth.viewmodel.AuthViewModel
import dev.pankaj.cleanarchitecture.presentation.auth.viewmodel.AuthViewModelFactory
import javax.inject.Inject
import dev.pankaj.cleanarchitecture.utils.*

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: AuthViewModelFactory
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        addObserver()
        binding.login.setOnClickListener { validateAndLogin() }
    }
    private fun addObserver(){
        viewModel.loginResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CallBack.Loading -> setLoadingIndicator(result.isLoading)
                is CallBack.Success -> handleLoginSuccess(result.data)
                is CallBack.Error -> showMessage(result.exception.message)
                is CallBack.Message -> showMessage(result.msg)
            }
        }

        viewModel.loginValidate.observe(viewLifecycleOwner) { callBack ->
            when (callBack) {
                is CallBack.Success -> {
                    if (callBack.data){
                        viewModel.login(LoginRequest(
                            binding.username.editText?.trimText()!!,
                            binding.password.editText?.trimText()!!)
                        )
                    }else {
                        showMessage("Something went wrong")
                    }
                }
                is CallBack.Error -> {}
                is CallBack.Message -> {
                    showMessage(callBack.msg)
                }
                is CallBack.Loading -> {}
            }
        }
    }

    private fun validateAndLogin() {
        viewModel.validateLogin(
            binding.password.editText?.trimText()?:"",
            binding.username.editText?.trimText()?:""
        )
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.show()
            binding.login.hide()
            binding.root.disable()
        } else {
            binding.loading.hide()
            binding.login.show()
            binding.root.enable()
        }
    }

    private fun handleLoginSuccess(data: LoginResponse) {
        findNavController()
            .navigateTo(R.id.action_loginFragment_to_navigation_home, true)
    }

    private fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}