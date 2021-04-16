package br.com.everis.sovamu.feature.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.everis.sovamu.databinding.ItemLayoutBinding
import br.com.everis.sovamu.feature.home.model.MockData

class ScrumAdapter(list: MutableList<MockData>) : RecyclerView.Adapter<ScrumAdapter.ViewHolder>() {

    private var items = list
    private lateinit var binding: ItemLayoutBinding

    class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MockData) {
            binding.apply {
                mock = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    fun updateList(characters: List<MockData>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mock = items[position]
        holder.apply {
            bind(mock)
        }
    }

    override fun getItemCount() = items.size
}