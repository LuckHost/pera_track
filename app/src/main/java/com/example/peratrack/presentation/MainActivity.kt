package com.example.peratrack.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.peratrack.R
import com.example.peratrack.app.App
import com.example.peratrack.databinding.ActivityMainBinding
import com.example.peratrack.di.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
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
        drawReceipts()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun drawReceipts() {
        vm.getAllReceipts()

        binding.mainText.text = vm.receipts.first().toString()
    }
}