package br.com.dqrtech.currencylayerconverter.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dqrtech.currencylayerconverter.R
import br.com.dqrtech.currencylayerconverter.ui.main.adapter.CurrenciesAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_layout.view.*

class CurrenciesAdapter (private val currencies: LinkedHashMap<String, String>) : RecyclerView.Adapter<DataViewHolder>() {

    lateinit var currencyAbbreviation : String
    lateinit var currency : String

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
        holder.bind(ArrayList<String>(currencies.keys).get(position), ArrayList<String>(currencies.values).get(position))
        holder.itemView.setOnClickListener {
            currencyAbbreviation = ArrayList<String>(currencies.keys).get(position)
            currency = ArrayList<String>(currencies.values).get(position)
            (holder.itemView.context as Activity).setResult(Activity.RESULT_OK, Intent()
                .putExtra("abbreviation", currencyAbbreviation)
                .putExtra("currency", currency)
            )
            (holder.itemView.context as Activity).finish()
        }
    }

    override fun getItemCount(): Int =
        currencies.size

    fun addCurrencies(currencies: LinkedHashMap<String, String>) {
        this.currencies.apply {
            clear()
            putAll(currencies)
        }
    }

}