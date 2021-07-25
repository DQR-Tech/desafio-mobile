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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.database.model.Exchange
import dev.keader.coinconversor.databinding.FragmentConverterBinding
import dev.keader.coinconversor.model.EventObserver
import dev.keader.coinconversor.view.mainAcitivity.MainActivity
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
            if (list.isNotEmpty()) {
                setupSpinners(list)
                handleWithEmptyData(false)
                // TODO: Handle with model
            }
        })

        uiViewModel.onUpdateDataResponse.observe(viewLifecycleOwner, EventObserver { success ->
            if (!success) {
                converterViewModel.checkIfNeedShowError()
                getSnackBarInstance(getString(R.string.connection_error)).show()
            }
        })

        converterViewModel.showError.observe(viewLifecycleOwner, EventObserver { show ->
            handleWithEmptyData(show)
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

    private fun getSnackBarInstance(text: String, duration: Int = Snackbar.LENGTH_LONG) : Snackbar {
        val activity = requireActivity()
        if (activity is MainActivity)
            return activity.getSnackBarInstance(text, duration)
        return Snackbar.make(binding.root, text, duration)
    }

    private fun handleWithEmptyData(showError: Boolean) {
        if (showError) {
            binding.groupError.visibility = View.VISIBLE
            binding.groupNormal.visibility = View.GONE
        }
        else {
            binding.groupError.visibility = View.GONE
            binding.groupNormal.visibility = View.VISIBLE
        }
    }

    private fun setupProgressBar() {
        uiViewModel.hasLoadInProgress.observe(viewLifecycleOwner, EventObserver { inProgress ->
            binding.progressIndicator.visibility = if (inProgress)
                View.VISIBLE
            else
                View.GONE
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
