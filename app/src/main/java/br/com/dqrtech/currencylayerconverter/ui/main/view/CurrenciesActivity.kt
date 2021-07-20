package br.com.dqrtech.currencylayerconverter.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dqrtech.currencylayerconverter.R
import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerHelper
import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerService
import br.com.dqrtech.currencylayerconverter.ui.base.ViewModelFactory
import br.com.dqrtech.currencylayerconverter.ui.main.adapter.CurrenciesAdapter
import br.com.dqrtech.currencylayerconverter.ui.main.viewmodel.CurrenciesViewModel
import br.com.dqrtech.currencylayerconverter.utils.Status
import kotlinx.android.synthetic.main.activity_currencies.*

class CurrenciesActivity : AppCompatActivity() {
    private lateinit var viewModel: CurrenciesViewModel
    private lateinit var adapter: CurrenciesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, ViewModelFactory(
            CurrencyLayerHelper(
            CurrencyLayerService.create())
        )
        ).get(CurrenciesViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CurrenciesAdapter(linkedMapOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        sv_search_currency.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun setupObservers() {
        viewModel.getCurrencyList().observe(this, {
            it?.let {resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        sv_search_currency.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { retrieveList(it.currencies!!) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        sv_search_currency.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        sv_search_currency.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(currencies: LinkedHashMap<String, String>) {
        adapter.apply {
            addCurrencies(currencies)
            notifyDataSetChanged()
        }
    }
}