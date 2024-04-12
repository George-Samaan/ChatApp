package com.route.chatapp.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.route.chatapp.R
import com.route.chatapp.base.BaseActivity
import com.route.chatapp.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {


    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavComponents()
    }

    override fun initViewModel() = ViewModelProvider(this)[AuthViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_auth

    private fun initNavComponents() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.auth_nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController
    }

}