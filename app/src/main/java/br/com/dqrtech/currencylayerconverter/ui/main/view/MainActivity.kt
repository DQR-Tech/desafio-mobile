package br.com.dqrtech.currencylayerconverter.ui.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.dqrtech.currencylayerconverter.R
import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerHelper
import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerService
import br.com.dqrtech.currencylayerconverter.ui.base.ViewModelFactory
import br.com.dqrtech.currencylayerconverter.ui.main.viewmodel.MainViewModel
import br.com.dqrtech.currencylayerconverter.utils.CurrencyConverter
import br.com.dqrtech.currencylayerconverter.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var abbreviation1 : String = ""
    private var abbreviation2 : String = ""
    private var rate1: Double = 0.0
    private var rate2: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this, ViewModelFactory(CurrencyLayerHelper(
            CurrencyLayerService.create()))).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        button.text = "from"
        button.setOnClickListener {
            val intent = Intent(this, CurrenciesActivity::class.java)
            startActivityForResult(intent, 1)
        }
        button2.text = "to"
        button2.setOnClickListener {
            val intent = Intent(this, CurrenciesActivity::class.java)
            startActivityForResult(intent, 2)
        }
        editTextNumberDecimal.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                convertValues()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0!!.length == 0) {
                    textView.text = ""
                }
            }

        })
    }

    private fun setupObservers() {
        viewModel.getCurrenciesRate(abbreviation1 + "," + abbreviation2).observe(this, {
            it?.let {resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val lista = ArrayList<Double>(resource.data?.conversionRates?.values)
                        rate1 = lista.get(0)
                        rate2 = lista.get(1)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "Aguarde...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun convertValues() {
        if(rate1 > 0.0 && rate2 > 0.0 && editTextNumberDecimal.text.length > 0) {
            textView.text = CurrencyConverter.convert(editTextNumberDecimal.text.toString().toDouble(), rate1, rate2).toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                abbreviation1 = data?.getStringExtra("abbreviation")!!
                button.text = abbreviation1
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                abbreviation2 = data?.getStringExtra("abbreviation")!!
                button2.text = abbreviation2
            }
        }
        if (abbreviation1.length == 3 && abbreviation2.length == 3) {
            setupObservers()
        }
        convertValues()
    }
}