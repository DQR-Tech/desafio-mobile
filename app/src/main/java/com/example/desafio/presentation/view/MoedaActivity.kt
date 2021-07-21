package com.example.desafio.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    private val moedaViewModel: MoedaViewModel by viewModel()
    lateinit var moeda:Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moeda)

        initView()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getAllMoedas()
    }

    private fun getAllMoedas() {
        moedaViewModel.getAllMoedas()
        moedaViewModel.moeda.observe(this) { mapMoeda ->
            moeda = mapMoeda
            val adapter = MoedaAdapter(this, mapMoeda)
            recycler_moeda.layoutManager = LinearLayoutManager(this)
            recycler_moeda.adapter = adapter
            progressBar_moeda.visibility = View.INVISIBLE
        }
    }

    override fun onClick(posicao: Int) {
        val codigo = ArrayList<String>(moeda.keys).get(posicao)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("CODIGO_KEY", codigo)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}