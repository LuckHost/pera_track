package com.luckhost.peratrack.presentation.authScreen

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.R
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.ActivityAuthorizationBinding
import com.luckhost.peratrack.di.AuthViewModelFactory
import javax.inject.Inject

class AuthorizationActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: AuthViewModelFactory
    private lateinit var vm: AuthViewModel
    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)
        vm = ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)

        binding.nextButton.setOnClickListener {
            Log.d("AuthVM", "Сохраняем параметры")
            vm.saveUserParams(
                login = binding.mailTextField.text.toString(),
                password = binding.imapTextField.text.toString()
            )
        }

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}