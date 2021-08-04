package dev.keader.coinconversor.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.keader.coinconversor.database.model.Currency
import dev.keader.coinconversor.databinding.ListItemCurrenciesBinding


class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.CurrencyAdapterViewHolder>(CurrencyAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapterViewHolder {
        return CurrencyAdapterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CurrencyAdapterViewHolder, position: Int) {
        val currency = getItem(position)
        holder.bind(currency)
    }

    class CurrencyAdapterViewHolder private constructor(private val binding: ListItemCurrenciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.currency = currency
        }

        companion object {
            fun from(parent: ViewGroup): CurrencyAdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCurrenciesBinding.inflate(layoutInflater, parent, false)
                return CurrencyAdapterViewHolder(binding)
            }
        }
    }

    private companion object : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }
        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }
}
