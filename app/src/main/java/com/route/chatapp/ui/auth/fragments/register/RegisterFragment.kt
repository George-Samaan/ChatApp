package com.route.chatapp.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.database.User
import com.route.chatapp.databinding.FragmentRegisterBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.home.HomeActivity


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
        startActivity(
            Intent(
                requireActivity(),
                HomeActivity::class.java
            )
                .putExtra(Constants.PASSED_USER, user)
        )
        requireActivity().finish()
    }


    // SO IMPORTANT FUNCTION
    // btorbot el viewmModel bl fragment
    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

}