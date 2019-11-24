package io.vinicius.androidcommon.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import io.vinicius.androidcommon.constant.MenuOption
import io.vinicius.androidcommon.databinding.RowHomeBinding

class HomeAdapter(private val items: Array<MenuOption>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>()
{
    val itemClick = MutableLiveData<MenuOption>()

    inner class ViewHolder(private val binding: RowHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuOption) = with(binding) {
            textName.text = item.menu
            root.setOnClickListener { itemClick.value = item }
        }
    }

    // region - Adapter
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder(RowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
    // endregion
}