package com.example.peratrack.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.peratrack.R
import com.example.peratrack.app.App
import com.example.peratrack.databinding.ActivityMainBinding
import com.example.peratrack.di.MainViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.Label
import com.google.api.services.gmail.model.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStreamReader
import java.net.SocketTimeoutException
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val TIMEOUT = 15000
private const val APPLICATION_NAME = "Email Extractor"
private val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()
private const val TOKENS_DIRECTORY_PATH = "tokens"
private val SCOPES = setOf(
    GmailScopes.GMAIL_LABELS,
    GmailScopes.GMAIL_READONLY,
    GmailScopes.GMAIL_METADATA
)

class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 9001

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        signIn()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private val MAX_FETCH_THREADS = Runtime.getRuntime().availableProcessors()

    val executors = Executors.newFixedThreadPool(MAX_FETCH_THREADS)

    val dispatcher = object : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            executors.execute(block)
        }
    }

    private tailrec fun Gmail.processMessages(
        user: String,
        label: Label,
        nextPageToken: String? = null,
        process: (Message) -> Unit
    ) {

        val messages = users().messages().list(user).apply {
            labelIds = listOf(label.id)
            pageToken = nextPageToken
            includeSpamTrash = true
        }.execute()
        messages.messages.forEach { message ->
            process(message)
        }

        if (messages.nextPageToken != null) {
            processMessages(user, label, messages.nextPageToken, process)
        }
    }


    private fun String.parseAddress(): String {
        return if (contains("<")) {
            substringAfter("<").substringBefore(">")
        } else {
            this
        }
    }


    private fun Gmail.processFroms(
        user: String,
        label: Label,
        process: (String) -> Unit
    ) {
        runBlocking(dispatcher) {
            processMessages(user, label) { m ->
                launch {
                    fun fetchAndProcess() {
                        try {
                            val message = users().messages().get(user, m.id).apply { format = "METADATA" }.execute()
                            message.payload.headers.find { it.name == "From" }?.let { from ->
                                process(from.value.parseAddress())
                            }
                        } catch (e: SocketTimeoutException) {
                            // Process eventual failures.
                            // Restart request on socket timeout.
                            e.printStackTrace()
                            fetchAndProcess()
                        } catch (e: Exception) {
                            // Process eventual failures.
                            e.printStackTrace()
                        }
                    }
                    fetchAndProcess()
                }
            }
        }
    }

    private fun getCredentials(httpTransport: HttpTransport): Credential? {
        val assetManager = applicationContext.assets
        val inputStream = assetManager.open("credentials.json")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inputStream))

        // Изменяем путь для хранения токенов на внутреннюю директорию приложения
        val tokensDirectoryPath = File(applicationContext.filesDir, "tokens")
        if (!tokensDirectoryPath.exists()) {
            tokensDirectoryPath.mkdirs() // Создаем директорию, если она не существует
        }

        val flow = GoogleAuthorizationCodeFlow
            .Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(FileDataStoreFactory(tokensDirectoryPath))  // Изменено на корректный путь
            .setAccessType("offline")
            .build()

        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }



    fun extract(labelName: String) {

        // Build a new authorized API client service.
        val httpTransport = NetHttpTransport()

        val service = Gmail
            .Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
            .setApplicationName(APPLICATION_NAME)
            .build()

        // Find the requested label
        val user = "me"
        val labelList = service.users().labels().list(user).execute()
        val label = labelList.labels
            .find { it.name == labelName } ?: error("Label $labelName is unknown.")


        // Process all From headers.
        val senders = mutableSetOf<String>()
        service.processFroms(user, label) {
            senders += it
        }

        senders.forEach{
            Log.d("MainVM", it)
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(GmailScopes.GMAIL_READONLY)) // Запросите доступ к Gmail API
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Получаем токен для использования в API
                val token = account?.idToken
                setupGmailService(token)
            } catch (e: ApiException) {
                Log.w("MainActivity", "Google sign-in failed", e)
            }
        }
    }

    private fun setupGmailService(token: String?) {
        // Выполняем сетевые запросы в фоновом потоке
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val httpTransport = NetHttpTransport()
                val jsonFactory = JacksonFactory.getDefaultInstance()
                val credential = GoogleCredential().setAccessToken(token)

                val gmailService = Gmail.Builder(httpTransport, jsonFactory, credential)
                    .setApplicationName("Your App Name")
                    .build()

                val query = "has:list"
                val request = gmailService.users().messages().list("me").setQ(query).execute()

                // Обновление UI после завершения сетевого запроса должно выполняться в основном потоке
                withContext(Dispatchers.Main) {
                    Log.d("MainActivity", request.messages.toString())
                    // Обновите UI с помощью данных
                }

            } catch (e: Exception) {
                // Обработка ошибок
                Log.e("MainActivity", "Error during Gmail API call", e)
            }
        }
    }


}