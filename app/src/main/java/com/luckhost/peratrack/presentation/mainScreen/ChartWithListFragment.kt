package com.luckhost.peratrack.presentation.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.FragmentChartWithListBinding
import com.luckhost.peratrack.di.MainViewModelFactory
import com.luckhost.peratrack.presentation.mainScreen.ui.PieChartElement
import com.luckhost.peratrack.presentation.mainScreen.ui.RecyclerReceiptAdapter
import javax.inject.Inject

class ChartWithListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var vm: MainViewModel
    private lateinit var binding: FragmentChartWithListBinding

    private lateinit var adapter: RecyclerReceiptAdapter // Adapter for recycle view

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity?.application as App).appComponent.inject(this)
        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        binding = FragmentChartWithListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.currentList.observe(activity as LifecycleOwner) {


            binding.pieChart.updateSectorsList(
                it.map { item ->
                    PieChartElement(
                        itemName = item.storeName,
                        value = item.totalAmount
                    )
                }
            )


            val manager = LinearLayoutManager(activity?.application)
            adapter = RecyclerReceiptAdapter(it)
            binding.recyclerView.layoutManager =
                manager
            binding.recyclerView.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChartWithListFragment()
    }
}