package com.example.desafio.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.desafio.R
import com.example.desafio.presentation.viewmodel.ConversorViewModel
import com.example.desafio.presentation.viewmodel.MoedaViewModel
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

        edt_valor.addTextChangedListener {
            if(!it.isNullOrBlank()){
                valor = it.toString().toDouble()
                if(edt_origem.length() > 2 && edt_destino.length() > 2)
                    conversorViewModel.getSearchMoedas("${edt_destino.text},${edt_origem.text}")
            }
        }

        conversorViewModel.moeda.observe(this) { mapMoeda ->
            //Com o plano Free não pode converter diretamente os valores so pode com o Dolar, então...
            //eu transformei a moeda de origem em dolar e mutipliquei por origem(Resumindo Fiz uma regra de 3)
            mapMoeda.forEach { moeda ->
                if(moeda.key == "USD${edt_origem.text}")
                    valorOrigem = 1/moeda.value

                if(moeda.key == "USD${edt_destino.text}")
                    valorDestino = moeda.value
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