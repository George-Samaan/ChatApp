package com.route.chatapp.ui.auth.fragments.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.database.User
import com.route.chatapp.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {


    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_register


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner, ::onEventChange)
    }

    private fun onEventChange(event: RegisterViewEvents) {
        when (event) {
            is RegisterViewEvents.NavigateToHome -> {
                navigateToHome(event.user)
            }
        }
    }

    private fun navigateToHome(user: User) {
        val action =
            RegisterFragmentDirections
                .actionRegiisterFragmentToHomeFragment(
                    user
                )
        findNavController().navigate(
            action
        )
    }


    // SO IMPORTANT FUNCTION
    // btorbot el viewmModel bl fragment
    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

}