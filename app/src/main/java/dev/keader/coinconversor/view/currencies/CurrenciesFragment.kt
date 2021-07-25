package dev.keader.coinconversor.view.currencies

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.databinding.FragmentCurrenciesBinding
import dev.keader.coinconversor.model.distinctUntilChanged
import dev.keader.coinconversor.model.removeOldEvents
import dev.keader.coinconversor.view.adapters.CurrencyAdapter

@AndroidEntryPoint
class CurrenciesFragment : Fragment(){

    private lateinit var binding: FragmentCurrenciesBinding
    private val currenciesViewModel: CurrenciesViewModel by viewModels()
    private val handler = Handler(Looper.myLooper()!!)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currencies, container, false)

        binding.currenciesViewModel = currenciesViewModel

        val adapter = CurrencyAdapter()
        binding.recyclerviewCurrencies.adapter = adapter

        currenciesViewModel.search.observe(viewLifecycleOwner, { searchText ->
            if (searchText.isEmpty())
                handleWithAllCurrencies()
            else {
                handler.removeOldEvents()
                // Schedule to make a search in 2 seconds
                handler.postDelayed({ handleWithSearchedCurrencies(searchText) }, 2000)
            }
        })

        currenciesViewModel.currenciesList.distinctUntilChanged().observe(viewLifecycleOwner, { list ->
            handleWithListVisibility(list.isEmpty())
            if (list.isNotEmpty())
                adapter.submitList(list)
        })

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if (currenciesViewModel.search.value!!.isEmpty())
                handleWithAllCurrencies()
            else
                handleWithSearchedCurrencies(currenciesViewModel.search.value!!)
        }

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun handleWithAllCurrencies() {
        if (binding.radioButtonOrderByCode.isChecked)
            currenciesViewModel.getAllElementsByCode()
        else
            currenciesViewModel.getAllElementsByName()
    }

    private fun handleWithSearchedCurrencies(search: String) {
        hideKeyBoard()
        if (binding.radioButtonOrderByCode.isChecked)
            currenciesViewModel.getSearchElementsByCode(search)
        else
            currenciesViewModel.getSearchElementsByName(search)
    }

    private fun hideKeyBoard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun handleWithListVisibility(isEmpty: Boolean) {
        if (isEmpty) {
            binding.labelError.visibility = View.VISIBLE
            binding.recyclerviewCurrencies.visibility = View.GONE
        }
        else {
            binding.labelError.visibility = View.GONE
            binding.recyclerviewCurrencies.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        handler.removeOldEvents()
        super.onDestroy()
    }
}
