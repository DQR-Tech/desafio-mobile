package com.example.desafio.presentation.view.moeda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio.R
import com.example.desafio.presentation.view.MainActivity
import com.example.desafio.presentation.viewmodel.MoedaViewModel
import kotlinx.android.synthetic.main.activity_moeda.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoedaActivity : AppCompatActivity() , OnClickItemMoedaListener {

    private val moedaViewModel: MoedaViewModel by viewModel()
    lateinit var moeda:Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moeda)

        initView()
    }

    private fun initView() {
        setupToolbar()
        getAllMoedas()
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Lista de Moedas"
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
        intent.putExtra(R.string.codigo_key.toString(), codigo)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}