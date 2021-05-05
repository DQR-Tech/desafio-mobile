package org.sabaini.desafiodqrtech.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.desafiodqrtech.databinding.FragmentConverterBinding
import org.sabaini.desafiodqrtech.ui.viewmodels.ConverterViewModel
import java.text.DecimalFormat


@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private val viewModel: ConverterViewModel by viewModels()
    private val decimalFormat = DecimalFormat("#,###.##")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentConverterBinding.inflate(inflater)

        viewModel.currencies.observe(viewLifecycleOwner, {
            var selectedPositionOrig = 0
            var selectedPositionDest = 0

            val spinnerItens = it.mapIndexed { p, v ->
                if (v.code == "BRL") {
                    selectedPositionDest = p
                }
                if (v.code == "USD") {
                    selectedPositionOrig = p
                }
                "${v.code} - ${v.name}"
            }

            val spinnerAdapter: ArrayAdapter<String> =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    spinnerItens
                )

            binding.originCurrency.adapter = spinnerAdapter
            binding.destinationCurrency.adapter = spinnerAdapter

            binding.originCurrency.setSelection(selectedPositionOrig)
            binding.destinationCurrency.setSelection(selectedPositionDest)
        })

        binding.convertButton.setOnClickListener {
            callConvert(binding)
        }

        viewModel.convertedValue.observe(viewLifecycleOwner, {
            binding.result.setText(decimalFormat.format(it))
        })

        binding.amountInput.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                callConvert(binding)
                return@OnKeyListener false
            }
            false
        })

        viewModel.inputError.observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                viewModel.displayInputErrorComplete()
            }
        })

        binding.showCurrenciesButton.setOnClickListener {
            findNavController().navigate(ConverterFragmentDirections.actionConverterFragmentToCurrenciesFragment())
        }

        binding.originCurrency.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                binding.textCurrencyOrigin.text = selectedItem.take(3)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.destinationCurrency.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                binding.textCurrencyDestiny.text = selectedItem.take(3)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        return binding.root
    }

    private fun callConvert(binding: FragmentConverterBinding) {
        val orig = binding.originCurrency.selectedItem.toString().take(3)
        val dest = binding.destinationCurrency.selectedItem.toString().take(3)
        val value = binding.amountInput.text.toString()
        viewModel.convert(orig, dest, value)
    }
}