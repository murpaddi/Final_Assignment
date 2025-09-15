package com.example.final_assignment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.final_assignment.R
import com.example.final_assignment.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import androidx.core.widget.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login_Fragment : Fragment() {

    private val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etUsername = view.findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = view.findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val tvError = view.findViewById<TextView>(R.id.tvError)

        btnLogin.setOnClickListener {
            vm.login(
                etUsername.text?.toString().orEmpty(),
                etPassword.text?.toString().orEmpty()
            )
        }

        etUsername.addTextChangedListener(afterTextChanged = {vm.clearError()})
        etPassword.addTextChangedListener(afterTextChanged = {vm.clearError()})

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.loadingState.collect { isLoading ->
                        btnLogin.isEnabled = !isLoading
                    }
                }
                launch {
                    vm.keypassState.collect { keypass ->
                        if (!keypass.isNullOrBlank()) {
                            findNavController().navigate(
                                R.id.action_login_Fragment_to_dashboard_Fragment,
                                bundleOf("keypass" to keypass)
                            )
                        }
                    }
                }

                launch {
                    vm.errorState.collect { msg ->
                        if (msg.isNullOrBlank()) {
                            tvError.visibility = View.GONE
                            tvError.text = ""
                        } else {
                            tvError.text = msg
                            tvError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_, container, false)
    }
}