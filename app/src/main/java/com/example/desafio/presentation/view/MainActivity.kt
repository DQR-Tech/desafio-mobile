package com.example.desafio.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.desafio.R
import com.example.desafio.presentation.view.moeda.MoedaActivity
import com.example.desafio.presentation.viewmodel.remote.ConversorViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val conversorViewModel: ConversorViewModel by viewModel()
    private var valor:Double = -1.0
    private var valorOrigem:Double = -1.0
    private var valorDestino:Double = -1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val codigo = intent.getStringExtra(R.string.codigo_key.toString())
        if(codigo != null) edt_destino.setText(codigo.toString())

        searchMoeda()
        setupViewModel()
    }

    private fun searchMoeda() {
        edt_valor.addTextChangedListener {
            if(!it.isNullOrBlank()){
                valor = it.toString().toDouble()
                if(edt_origem.length() > 2 && edt_destino.length() > 2)
                    conversorViewModel.getSearchMoedas()
            }
        }
    }
    
    private fun setupViewModel() {
        /* Com o plano Free não pode converter diretamente os valores, apenas em Dolar, então...
        transformei a moeda de origem em dolar e mutipliquei pelo destino (Resumindo Fiz uma regra de 3) */

        conversorViewModel.moeda.observe(this) { mapMoeda ->
            mapMoeda.forEach { moeda ->
                if(moeda.key == "USD${edt_origem.text}") valorOrigem = 1/moeda.value
                if(moeda.key == "USD${edt_destino.text}") valorDestino = moeda.value
            }

            if(valorOrigem != -1.0 && valorDestino != -1.0)
                txt_valor.text = "%.4f".format(valorDestino*valorOrigem*valor)
        }
    }
    
    fun onclick(view: View) {
        val intent = Intent(this, MoedaActivity::class.java)
        startActivity(intent)
    }
}