package dev.keader.coinconversor.view.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.keader.coinconversor.R
import dev.keader.coinconversor.databinding.FragmentConverterBinding

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private lateinit var binding: FragmentConverterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_converter, container, false)

        return binding.root
    }
}
