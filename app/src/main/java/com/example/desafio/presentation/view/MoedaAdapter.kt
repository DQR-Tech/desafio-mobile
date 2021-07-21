package com.example.desafio.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.R
import kotlinx.android.synthetic.main.moeda_item.view.*

class MoedaAdapter(
    val listener: OnClickItemMoedaListener,
    var linkedHashMap:Map<String, String>
): RecyclerView.Adapter<MoedaAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moeda_item, parent, false)
        return viewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val codigo = ArrayList<String>(linkedHashMap.keys).get(position)
        val pais = ArrayList<String>(linkedHashMap.values).get(position)
        with(holder.itemView){
            txt_nomeMoeda.text = pais
            txt_codigoMoeda.text = codigo
        }
    }

    override fun getItemCount(): Int = linkedHashMap.size

    class viewHolder(itemView: View, listener: OnClickItemMoedaListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener { listener.onClick(adapterPosition) }
        }
    }
}
interface OnClickItemMoedaListener{
    fun onClick(posicao:Int)
}