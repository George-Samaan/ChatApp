package com.route.chatapp.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.route.chatapp.R
import com.route.chatapp.base.BaseFragment
import com.route.chatapp.database.User
import com.route.chatapp.databinding.FragmentLoginBinding
import com.route.chatapp.ui.Constants
import com.route.chatapp.ui.auth.AuthActivity
import com.route.chatapp.ui.home.HomeActivity

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]


    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.checkUserLoggedIn()

    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginViewEvent.NavigateToRegister -> {
                    navigateToRegister()
                }

                is LoginViewEvent.NavigateToHome -> {
                    navigateToHome(event.user)
                }
            }

        }
    }

    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
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

    private fun navigateToRegister() {
        if (activity == null) return
        (activity as AuthActivity).navController.navigate(
            R.id.action_loginFragment_to_regiisterFragment
        )
    }


}