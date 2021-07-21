package com.example.desafio.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.desafio.R
import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.presentation.view.moeda.MoedaActivity
import com.example.desafio.presentation.viewmodel.local.conversor.DeleteConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.InsertConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.SelectConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.VerificarConversorViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.SelectViewModel
import com.example.desafio.presentation.viewmodel.remote.ConversorViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val conversorViewModel: ConversorViewModel by viewModel()
    private val insertViewModel: InsertConversorViewModel by viewModel()
    private val verificarViewModel: VerificarConversorViewModel by viewModel()
    private val deleteViewModel: DeleteConversorViewModel by viewModel()
    private val selectViewModel: SelectConversorViewModel by viewModel()
    lateinit var conversorDto: ConversorDto
    private var valor:Double = 0.0
    private var valorOrigem:Double = 0.0
    private var valorDestino:Double = 0.0
    var listCodigo = ArrayList<String>()
    private val selectMoedalViewModel: SelectViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        searchMoeda()
        setupViewModel()
        setupLocal()
        getCodigoMoedas()
    }

    private fun searchMoeda() {
        edt_valor.addTextChangedListener {
            if(!it.isNullOrBlank()){
                valor = it.toString().toDouble()
                if(autoComplete_origem.length() > 2 && autoComplete_destino.length() > 2)
                    selectViewModel.getAllMoedas()
            }
        }
    }
    
    private fun setupViewModel() {
        conversorViewModel.getSearchMoedas()
        conversorViewModel.moeda.observe(this) { mapMoeda ->
            conversorDto = mapMoeda
            if(!conversorDto.moedas.isNullOrEmpty()) verificarViewModel.verificar()
        }
        verificarViewModel.verificado.observe(this) {jaExiste ->
            if(jaExiste) deleteViewModel.deleteMovie()

            insertViewModel.insertMoeda(conversorDto)
        }
    }

    fun setupLocal(){
        /* Com o plano Free não pode converter diretamente os valores, apenas em Dolar, então...
        transformei a moeda de origem em dolar e mutipliquei pelo destino (Resumindo Fiz uma regra de 3) */

        selectViewModel.allMoedas.observe(this) { conversor ->
            conversorDto = conversor
            conversorDto.moedas!!.forEach { moeda ->
                if(moeda.key == "USD${autoComplete_origem.text}") valorOrigem = 1/moeda.value
                if(moeda.key == "USD${autoComplete_destino.text}") valorDestino = moeda.value
            }

            txt_valor.text = "%.4f".format(valorDestino*valorOrigem*valor)
        }
        selectViewModel.getAllMoedas()
    }

    private fun getCodigoMoedas() {
        selectMoedalViewModel.allMoedas.observe(this) { moedaLocal ->
            moedaLocal.moedas!!.forEach { moeda -> listCodigo.add(moeda.key) }
            adapterAutoComplete(autoComplete_origem, listCodigo.toTypedArray())
            adapterAutoComplete(autoComplete_destino, listCodigo.toTypedArray())
        }
        selectMoedalViewModel.getAllMoedas()
    }

    private fun adapterAutoComplete(autoCompleteTextView: AutoCompleteTextView, arrayString: Array<String>) {
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, arrayString)
        autoCompleteTextView.setAdapter(adapter)
    }

    fun onclick(view: View) {
        val intent = Intent(this, MoedaActivity::class.java)
        startActivity(intent)
    }
}