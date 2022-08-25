package com.cherry.testapp5p.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cherry.testapp5p.R
import com.cherry.testapp5p.databinding.FragmentUserProfileBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    private val viewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        setViewsUp()
        return binding.root
    }

    private fun setViewsUp() {
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        }
        lifecycleScope.launch {

            viewModel.currentUser.collect { user ->
                user?.let {
                    binding.etUserName.setText(user.name)
                    binding.etUserSurname.setText(user.surname)
                    binding.etUserPhone.setText(user.fullPhoneNumber)
                }
            }
        }
    }
}