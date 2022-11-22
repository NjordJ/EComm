package com.iruda.ecomm.presentation.account.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iruda.ecomm.databinding.FragmentLoginBinding
import com.iruda.ecomm.presentation.account.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw  RuntimeException("FragmentLoginBinding is null")

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

        binding.apply {
            textInputEditTextEmail.setText("kminchelle")
            textInputEditTextPassword.setText("0lelplR")
            buttonSignIn.setOnClickListener {
                authorizeUser(
                    email = textInputEditTextEmail.text.toString(),
                    password = textInputEditTextPassword.text.toString()
                )
                val visibilityArray = intArrayOf(VIEWGONE, VIEWVISIBLE)
                val action =
                    LoginFragmentDirections.actionLoginFragmentToAccountFragment(
                        visibilityArray
                    )
                findNavController().navigate(action)
            }
            buttonSignUp.setOnClickListener {
                lifecycleScope.launch {
                    val user = viewModel.getUser()
                    //TODO: Remove Log UserProto
                    Log.d("UserProto", user.first().id.toString())
                    Log.d("UserProto", user.first().token)
                    Log.d("UserProto", user.first().isAuthorized.toString())
                }
            }
        }
    }

    private fun authorizeUser(email: String, password: String) {
        lifecycleScope.launch {
            viewModel.authorize(email = email, password = password)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val VIEWGONE = 8
        private const val VIEWVISIBLE = 0
    }

}