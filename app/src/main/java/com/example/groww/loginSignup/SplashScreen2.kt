package com.example.groww.loginSignup

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.groww.R
import kotlinx.android.synthetic.main.fragment_splash_screen2.*
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SplashScreen2 : Fragment(R.layout.fragment_splash_screen2) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            CoroutineScope(Dispatchers.Main).launch {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_splashScreen2_to_splashScreen3)
            }
        }, 1500)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}