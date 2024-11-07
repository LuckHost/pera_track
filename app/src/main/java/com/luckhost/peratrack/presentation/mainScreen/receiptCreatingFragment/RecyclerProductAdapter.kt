package com.luckhost.peratrack.presentation.mainScreen.receiptCreatingFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luckhost.peratrack.R
import com.luckhost.peratrack.databinding.ItemProductBinding
import com.peratrack.domain.models.Product
import com.peratrack.domain.models.Product.CountType

class RecyclerProductAdapter(
    private val data: List<Product>
) : RecyclerView.Adapter<RecyclerProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) :
            RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = data[position]

        val context = holder.binding.root.context

        with(holder.binding) {
            productName.text = product.name

            productCount.text =
                when (product.countType) {
                    CountType.UNITS -> {
                        context.getString(
                            R.string.fragment_reccreat_count_field_units_text,
                            product.count.toString()
                        )
                    }
                    CountType.KILOGRAMS -> {
                        context.getString(
                            R.string.fragment_reccreat_count_field_kilograms_text,
                            product.count.toString()
                        )
                    }
                    CountType.GRAMS -> {
                        context.getString(
                            R.string.fragment_reccreat_count_field_grams_text,
                            product.count.toString()
                        )
                    }
                    else -> {
                        product.count.toString()
                    }
                }

            productAmount.text = context.getString(
                R.string.fragment_reccreat_amount_field_text,
                product.amount.toString(),
                product.currency
            )

        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}