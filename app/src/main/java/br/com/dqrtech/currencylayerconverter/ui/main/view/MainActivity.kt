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
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var abbreviation1 : String = ""
    private var currency1: String = ""
    private var currency2: String = ""
    private var abbreviation2 : String = ""
    private var rate1: BigDecimal = BigDecimal.ZERO
    private var rate2: BigDecimal = BigDecimal.ZERO

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
        btn_from.setOnClickListener {
            val intent = Intent(this, CurrenciesActivity::class.java)
            startActivityForResult(intent, 1)
        }
        btn_to.setOnClickListener {
            val intent = Intent(this, CurrenciesActivity::class.java)
            startActivityForResult(intent, 2)
        }
        et_from_value.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                convertValues()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0!!.length == 0) {
                    tv_to_value.text = ""
                }
            }

        })
    }

    private fun setupObservers() {
        viewModel.getCurrenciesRate(abbreviation1 + "," + abbreviation2).observe(this, {
            it?.let {resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val lista = ArrayList<BigDecimal>(resource.data?.conversionRates?.values)
                        rate1 = lista.get(0)
                        if (lista.size > 1) {
                            rate2 = lista.get(1)
                        } else {
                            rate2 = rate1
                        }
                        convertValues()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun convertValues() {
        if(rate1 > BigDecimal.ZERO && rate2 > BigDecimal.ZERO && et_from_value.text!!.length > 0) {
            tv_to_value.text = String.format(Locale.getDefault(), CurrencyConverter.convert(et_from_value.text.toString().toBigDecimal(), rate1, rate2).toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                abbreviation1 = data?.getStringExtra("abbreviation")!!
                currency1 = data.getStringExtra("currency")!!
                btn_from.text = abbreviation1
                tv_from.text = currency1
            }
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                abbreviation2 = data?.getStringExtra("abbreviation")!!
                currency2 = data.getStringExtra("currency")!!
                btn_to.text = abbreviation2
                tv_to.text = currency2
            }
        }
        if (abbreviation1.length == 3 && abbreviation2.length == 3) {
            setupObservers()
        }
    }
}