package dev.keader.coinconversor.view.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.databinding.FragmentCurrenciesBinding

@AndroidEntryPoint
class CurrenciesFragment : Fragment(){

    private lateinit var binding: FragmentCurrenciesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currencies, container, false)

        return binding.root
    }
}
