package com.luckhost.peratrack.presentation.mainScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.R
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.ActivityMainBinding
import com.luckhost.peratrack.di.MainViewModelFactory
import com.luckhost.peratrack.presentation.mainScreen.chartWithListFragment.ChartWithListFragment
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, ChartWithListFragment.newInstance())
            .commit()

        vm.updateCurrentList()
    }

    fun changeFragmentToReceiptCreating() {

    }
}