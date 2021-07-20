package com.example.desafio.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio.R
import com.example.desafio.data.remote.network.RetrofitInstance
import com.example.desafio.data.remote.repository.MoedaImpl
import com.example.desafio.domain.usecase.GetMoeda
import com.example.desafio.presentation.viewmodel.MoedaViewModel
import kotlinx.android.synthetic.main.activity_moeda.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoedaActivity : AppCompatActivity() , OnClickItemMoedaListener{

//    private val moedaViewModel: MoedaViewModel by viewModel()
    lateinit var moedaViewModel:MoedaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moeda)

        val usecase = GetMoeda(MoedaImpl(RetrofitInstance().retrofitInstance()))
        moedaViewModel = ViewModelProvider(this, MoedaViewModel.viewModelFactory(usecase)).get(MoedaViewModel::class.java)

        moedaViewModel.getAllMoedas()
        moedaViewModel.moeda.observe(this) { mapMoeda ->
            val adapter = MoedaAdapter(this, mapMoeda)
            recycler_moeda.layoutManager = LinearLayoutManager(this)
            recycler_moeda.adapter = adapter
        }
    }

    override fun onClick(posicao: Int) {

    }
}