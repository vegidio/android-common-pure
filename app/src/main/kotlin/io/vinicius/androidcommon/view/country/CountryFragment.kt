package io.vinicius.androidcommon.view.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.vinicius.androidcommon.custom.BaseFragment
import io.vinicius.androidcommon.databinding.FragmentCountryBinding
import io.vinicius.androidcommon.viewmodel.CountryViewModel

class CountryFragment : BaseFragment()
{
    companion object {
        @Suppress("unused")
        fun newInstance() = CountryFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(CountryViewModel::class.java) }
    private var ignoreValue = ""

    private lateinit var binding: FragmentCountryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        binding.buttonRun.setOnClickListener {
            loadData()
        }
    }

    // region - Private Methods
    private fun loadData()
    {
        val codes = arrayOf("AS", "BY", "BR", "ES", "GR", "HR", "IR", "LT", "MZ", "PS", "RS", "RU", "SE", "UA")
        val filtered = codes.filter { it != ignoreValue }
        val code = filtered.shuffled().first()

        viewModel.getCountryByCode(code).observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                ignoreValue = result.getOrNull()?.alpha2Code ?: ""
                binding.textCountryName.text = result.getOrNull()?.name
                binding.textCountryCapital.text = result.getOrNull()?.capital
            }
        })
    }
    // endregion
}