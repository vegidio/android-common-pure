package io.vinicius.androidcommon.view.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.vinicius.androidcommon.custom.BaseFragment
import io.vinicius.androidcommon.databinding.FragmentCountryBinding
import io.vinicius.androidcommon.viewmodel.CountryViewModel
import kotlinx.coroutines.launch

class CountryFragment : BaseFragment()
{
    companion object {
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

    override fun bindViewModel()
    {
        viewModel.country.observe(this, Observer {
            binding.textCountryName.text = it.name
            binding.textCountryCapital.text = it.capital
        })
    }

    // region - Private Methods
    private fun loadData()
    {
        val codes = arrayOf("AS", "BY", "BR", "ES", "GR", "HR", "IR", "LT", "MZ", "PS", "RS", "RU", "SE", "UA")
        val filtered = codes.filter { it != ignoreValue }
        val code = filtered.shuffled().first()

        lifecycleScope.launch {
            viewModel.getCountryByCode(code)
        }
    }
    // endregion
}