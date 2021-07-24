package dev.keader.coinconversor.view.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.database.model.Exchange
import dev.keader.coinconversor.databinding.FragmentConverterBinding
import dev.keader.coinconversor.model.EventObserver
import dev.keader.coinconversor.view.mainAcitivity.UIViewModel

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private lateinit var binding: FragmentConverterBinding
    private val converterViewModel: ConverterViewModel by viewModels()
    private val uiViewModel: UIViewModel by activityViewModels()
    private val navController
        get() = findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_converter, container, false)
        binding.convertViewModel = converterViewModel

        converterViewModel.exchanges.observe(viewLifecycleOwner, { list ->
            val isEmpty = list.isEmpty()
            handleWithEmptyData(isEmpty)
            if (!isEmpty) {
                setupSpinners(list)
                // TODO: Handle with model
            }
        })

        converterViewModel.eventListClick.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(ConverterFragmentDirections.toCurrenciesFragment())
        })

        converterViewModel.eventRetryClick.observe(viewLifecycleOwner, EventObserver {
            uiViewModel.updateData()
        })

        converterViewModel.eventConvertClick.observe(viewLifecycleOwner, EventObserver {
            // TODO: Handle with click
        })

        setupProgressBar()
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun handleWithEmptyData(isEmpty: Boolean) {
        if (isEmpty) {
            binding.groupError.visibility = View.VISIBLE
            binding.groupNormal.visibility = View.GONE
        }
        else {
            binding.groupError.visibility = View.GONE
            binding.groupNormal.visibility = View.VISIBLE
        }
    }

    private fun setupProgressBar() {
        // TODO: Doing so much stuffs into main thread :/
        uiViewModel.hasLoadInProgress.observe(viewLifecycleOwner, { inProgress ->
            if (inProgress) {
                binding.progressIndicator.visibility = View.VISIBLE
                // If group normal is invisible, then button is already invisible
                if (binding.groupNormal.visibility == View.VISIBLE) {
                    binding.buttonConvert.visibility = View.GONE
                }
            }
            else {
                binding.progressIndicator.visibility = View.GONE
                if (binding.groupNormal.visibility == View.VISIBLE) {
                    binding.buttonConvert.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupSpinners(exchanges: List<Exchange>) {
        val context = requireContext()
        val adapterList = exchanges.map { it.code }
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, adapterList)
        binding.spinnerInputOrigin.setAdapter(adapter)
        binding.spinnerInputDestination.setAdapter(adapter)
        // Set default value
        if (adapterList.contains("USD") && adapterList.contains("BRL")) {
            if (converterViewModel.originalCoin.value?.isEmpty() == true)
                converterViewModel.originalCoin.value = "USD"
            if (converterViewModel.destinationCoin.value?.isEmpty() == true)
                converterViewModel.destinationCoin.value = "BRL"
        }
    }
}
