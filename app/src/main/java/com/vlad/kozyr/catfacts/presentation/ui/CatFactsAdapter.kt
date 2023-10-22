package com.vlad.kozyr.catfacts.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vlad.kozyr.catfacts.databinding.ItemCatInfoBinding
import com.vlad.kozyr.catfacts.domain.model.CatImageWithFact

class CatFactsAdapter(
    private val catFacts: List<CatImageWithFact>
) : RecyclerView.Adapter<CatFactsAdapter.CatInfoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CatInfoViewHolder(ItemCatInfoBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = catFacts.size

    override fun onBindViewHolder(holder: CatInfoViewHolder, position: Int) {
        holder.bind(catFacts[position])
    }

    class CatInfoViewHolder(private val binding: ItemCatInfoBinding) : ViewHolder(binding.root) {

        fun bind(catInfo: CatImageWithFact) = with(binding) {
            Glide.with(root)
                .load(catInfo.imageUrl)
                .into(ivImage)
            tvFact.text = catInfo.fact
        }
    }
}