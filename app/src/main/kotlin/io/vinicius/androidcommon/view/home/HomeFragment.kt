package io.vinicius.androidcommon.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.vinicius.androidcommon.R
import io.vinicius.androidcommon.constant.MenuOption
import io.vinicius.androidcommon.custom.BaseFragment
import io.vinicius.androidcommon.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : BaseFragment()
{
    companion object {
        fun newInstance() = HomeFragment()
    }

    private val adapter = HomeAdapter(arrayOf(MenuOption.COUNTRY, MenuOption.FIREBASE_FIRESTORE))
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMain.setHasFixedSize(true)
        binding.recyclerMain.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.recyclerMain.layoutManager = LinearLayoutManager(context)
        binding.recyclerMain.adapter = adapter
    }

    override fun bindViewModel()
    {
        adapter.itemClick.observe(this, Observer {
            when(it) {
                MenuOption.COUNTRY -> navigation.navigate(R.id.homeToCountry)
                else -> Timber.w("Unexpected selection...")
            }
        })
    }
}