package com.luckhost.peratrack.presentation.mainScreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luckhost.peratrack.databinding.ItemReceiptBinding
import com.peratrack.domain.models.Receipt

/**
 * Recycler view adapter that for receipts scroll list
 */
class RecyclerReceiptAdapter(
    private val data: List<Receipt>
) : RecyclerView.Adapter<RecyclerReceiptAdapter.ReceiptViewHolder>() {

    class ReceiptViewHolder(val binding: ItemReceiptBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReceiptBinding.inflate(inflater, parent, false)

        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt = data[position] // Получение человека из списка данных по позиции
        with(holder.binding) {

            storeNameText.text = receipt.storeName // Отрисовка имени пользователя
            dateText.text = receipt.date.toString() // Отрисовка компании пользователя
            receiptAmount.text = receipt.totalAmount.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}