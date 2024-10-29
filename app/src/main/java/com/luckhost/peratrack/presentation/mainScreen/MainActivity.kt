package com.luckhost.peratrack.presentation.mainScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.luckhost.peratrack.R
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.ActivityMainBinding
import com.luckhost.peratrack.di.MainViewModelFactory
import com.luckhost.peratrack.presentation.authScreen.AuthorizationActivity
import com.luckhost.peratrack.presentation.mainScreen.ui.PieChartElement
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.pieChart.updateSectorsList(
            listOf(
                PieChartElement("Молоко", 1235f),
                PieChartElement("Кокос", 365f),
                PieChartElement("Банан", 344f),
            )
        )

        binding.textView2.text = vm.getAllReceiptsUseCase.execute().toString()

        binding.startAuthActivity.setOnClickListener {
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }
    }
}