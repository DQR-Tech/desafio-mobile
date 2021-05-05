package org.sabaini.desafiodqrtech.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.desafiodqrtech.adapters.CurrenciesListAdapter
import org.sabaini.desafiodqrtech.databinding.FragmentCurrenciesBinding
import org.sabaini.desafiodqrtech.ui.viewmodels.CurrenciesViewModel

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {

    private val viewModel: CurrenciesViewModel by viewModels()
    private val adapter = CurrenciesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCurrenciesBinding.inflate(inflater)

        binding.currenciesList.adapter = adapter
        binding.currenciesList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.currencies.observe(viewLifecycleOwner, {
            adapter.currencies = it
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        binding.chipName.setOnClickListener {
            adapter.orderByName = true
            adapter.filter.filter("")
            binding.chipCode.isChecked = false
        }

        binding.chipCode.setOnClickListener {
            adapter.orderByCode = true
            adapter.filter.filter("")
            binding.chipName.isChecked = false
        }

        return binding.root
    }
}