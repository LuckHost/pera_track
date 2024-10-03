package com.luckhost.peratrack.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModelProvider
import javax.mail.*
import javax.mail.internet.MimeBodyPart
import javax.mail.search.FlagTerm
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.luckhost.peratrack.R
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.ActivityMainBinding
import com.luckhost.peratrack.di.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Properties
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true

    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        enableEdgeToEdge()
        setContentView(binding.root)
        fetchEmails()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun signIn() {
        val context = this

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .setAutoSelectEnabled(true)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = CredentialManager.create(this)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                Log.d("MainView", result.toString())
            } catch (e: GetCredentialException) {
                Log.e("MainView", e.toString())
            }
        }
    }

    fun fetchEmails() {

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }
}