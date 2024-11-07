package com.luckhost.peratrack.presentation.mainScreen.receiptCreatingFragment

import com.luckhost.peratrack.di.MainViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.luckhost.peratrack.app.App
import com.luckhost.peratrack.databinding.FragmentReceiptCreatingBinding
import com.luckhost.peratrack.presentation.mainScreen.MainViewModel
import javax.inject.Inject

class ReceiptCreatingFragment : Fragment() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel
    private lateinit var binding: FragmentReceiptCreatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).appComponent.inject(this)
        vm = ViewModelProvider(requireActivity(), vmFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReceiptCreatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedIndex = arguments?.getInt(ARG_RECEIPT_INDEX) ?: return

        // Извлекаем нужный элемент из ViewModel
        val receipt = vm.currentList.value?.getOrNull(selectedIndex)

    }

    companion object {
        private const val ARG_RECEIPT_INDEX = "receipt_index"

        fun newInstance(index: Int): ReceiptCreatingFragment {
            val fragment = ReceiptCreatingFragment()
            val args = Bundle()
            args.putInt(ARG_RECEIPT_INDEX, index)
            fragment.arguments = args
            return fragment
        }
    }
}
