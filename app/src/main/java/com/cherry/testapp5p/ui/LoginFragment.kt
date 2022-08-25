package com.cherry.testapp5p.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cherry.testapp5p.R
import com.cherry.testapp5p.databinding.FragmentLoginBinding
import com.cherry.testapp5p.domain.EmptyFieldsException
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setViewsUp()
        return binding.root
    }

    private fun setViewsUp() {
        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.etPhoneNumberForLogin.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                try {
                    viewModel.loginWithPhoneAndPassword(phoneNumber, password)

                    navigateToProfileFragment(phoneNumber)
                } catch (e: EmptyFieldsException) {
                    Toast.makeText(
                        context,
                        "You need to enter your phone number and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun navigateToProfileFragment(phoneNumber: String) {
        viewModel.getUser(phoneNumber)
        findNavController().navigate(R.id.action_loginFragment_to_userProfileFragment)
    }
}