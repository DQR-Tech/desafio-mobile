package br.com.dqrtech.currencylayerconverter.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.dqrtech.currencylayerconverter.R
import br.com.dqrtech.currencylayerconverter.ui.main.adapter.CurrenciesAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_layout.view.*

class CurrenciesAdapter (private val currencies: LinkedHashMap<String, String>) : RecyclerView.Adapter<DataViewHolder>(), Filterable {

    lateinit var currencyAbbreviation : String
    lateinit var currency : String
    var currenciesFiltered = LinkedHashMap<String, String>()

    init {
        currenciesFiltered = currencies
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currencyAbbreviation: String, currenctName: String) {
            itemView.apply {
                textViewCurrency.text = "$currencyAbbreviation - $currenctName"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(ArrayList<String>(currenciesFiltered.keys).get(position), ArrayList<String>(currenciesFiltered.values).get(position))
        holder.itemView.setOnClickListener {
            currencyAbbreviation = ArrayList<String>(currenciesFiltered.keys).get(position)
            currency = ArrayList<String>(currenciesFiltered.values).get(position)
            (holder.itemView.context as Activity).setResult(Activity.RESULT_OK, Intent()
                .putExtra("abbreviation", currencyAbbreviation)
                .putExtra("currency", currency)
            )
            (holder.itemView.context as Activity).finish()
        }
    }

    override fun getItemCount(): Int =
        currenciesFiltered.size

    fun addCurrencies(currencies: LinkedHashMap<String, String>) {
        this.currencies.apply {
            clear()
            putAll(currencies)
        }
        this.currenciesFiltered.apply {
            clear()
            putAll(currencies)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    currenciesFiltered = currencies
                } else {
                    var resultMap = LinkedHashMap<String, String>()
                    resultMap.putAll(currencies.filter { x -> x.key.contains(charSearch, true) || x.value.contains(charSearch, true) } as LinkedHashMap<String, String>)
                    currenciesFiltered = resultMap
                }
                val filterResults = FilterResults()
                filterResults.values = currenciesFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currenciesFiltered = results?.values as LinkedHashMap<String, String>
                notifyDataSetChanged()
            }

        }
    }


}