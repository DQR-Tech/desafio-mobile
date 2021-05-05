package org.sabaini.desafiodqrtech.adapters

import androidx.recyclerview.widget.RecyclerView
import org.sabaini.desafiodqrtech.databinding.CurrenciesListItemBinding
import org.sabaini.desafiodqrtech.entities.Currency

class CurrenciesListViewHolder(
    private var binding: CurrenciesListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: Currency) {
        binding.currenciesItemName.text = currency.code
        binding.currenciesItemCountry.text = currency.name
    }
}