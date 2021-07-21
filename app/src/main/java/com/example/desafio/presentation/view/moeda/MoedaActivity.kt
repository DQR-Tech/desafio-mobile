package com.example.desafio.presentation.view.moeda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio.R
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.presentation.view.MainActivity
import com.example.desafio.presentation.viewmodel.local.moeda.DeleteViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.InsertViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.SelectViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.VerificarViewModel
import com.example.desafio.presentation.viewmodel.remote.MoedaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_moeda.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoedaActivity : AppCompatActivity() , OnClickItemMoedaListener {

    private val moedaViewModel: MoedaViewModel by viewModel()
    private val insertViewModel: InsertViewModel by viewModel()
    private val verificarViewModel: VerificarViewModel by viewModel()
    private val deleteViewModel: DeleteViewModel by viewModel()
    private val selectViewModel: SelectViewModel by viewModel()
    lateinit var moedaDto:MoedasDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moeda)

        initView()
    }

    private fun initView() {
        setupToolbar()
        getAllMoedas()
        verificarMoeda()
        setupLocal()
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Lista de Moedas"
    }

    private fun getAllMoedas() {
        moedaViewModel.getAllMoedas()
        moedaViewModel.moeda.observe(this) { mapMoeda ->
            moedaDto = mapMoeda
            if(!moedaDto.moedas.isNullOrEmpty())
                verificarViewModel.verificar()
        }
    }

    private fun setupAdapter(moedaDto: MoedasDto) {
        recycler_moeda.layoutManager = LinearLayoutManager(this)
        recycler_moeda.adapter = MoedaAdapter(this, moedaDto.moedas!!)
        progressBar_moeda.visibility = View.INVISIBLE
    }

    private fun verificarMoeda() {
        verificarViewModel.verificado.observe(this) { jaExiste ->
            //Se houver uma atualização, vai excluir e adicionar a atualização no banco local
            if(jaExiste)
                deleteViewModel.deleteMovie()

            insertViewModel.insertMoeda(moedaDto)
        }
    }

    private fun setupLocal() {
        selectViewModel.allMoedas.observe(this) { moedaLocal ->
            moedaDto = moedaLocal
            setupAdapter(moedaLocal)
        }
        selectViewModel.getAllMoedas()
    }

    override fun onClick(posicao: Int) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}