package org.sabaini.desafiodqrtech.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.sabaini.desafiodqrtech.databinding.CurrenciesListItemBinding
import org.sabaini.desafiodqrtech.entities.Currency
import java.util.*

class CurrenciesListAdapter : RecyclerView.Adapter<CurrenciesListViewHolder>(), Filterable {

    var orderByName = false
    var orderByCode = false

    var currenciesFilterList = emptyList<Currency>()

    var currencies = emptyList<Currency>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                CurrenciesListDiffCallback(field, value)
            )
            result.dispatchUpdatesTo(this)
            currenciesFilterList = value
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesListViewHolder {
        return CurrenciesListViewHolder(
            CurrenciesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrenciesListViewHolder, position: Int) {
        val currency = currenciesFilterList[position]
        holder.bind(currency)
    }

    override fun getItemCount(): Int = currenciesFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    currenciesFilterList = currencies
                } else {
                    val resultList = mutableListOf<Currency>()
                    for (row in currenciesFilterList) {
                        if (row.code.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)) ||
                            row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    currenciesFilterList = resultList
                }

                if (orderByName) {
                    currenciesFilterList =
                        currenciesFilterList.sortedBy { it.name } as MutableList<Currency>
                    orderByName = false
                } else if (orderByCode) {
                    currenciesFilterList =
                        currenciesFilterList.sortedBy { it.code } as MutableList<Currency>
                    orderByCode = false
                }

                val filterResults = FilterResults()
                filterResults.values = currenciesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currenciesFilterList = results?.values as List<Currency>
                notifyDataSetChanged()
            }

        }
    }
}