package com.shopmax.app.ui.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shopmax.app.databinding.PackageItemBinding
import com.shopmax.app.network.model.getquote.Package
import com.shopmax.app.R

class QuoteAdapter(val clicked: EditQuote): RecyclerView.Adapter<QuoteHolder>() {
    private var pack:MutableList<Package> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {
        val binding: PackageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.package_item, parent, false)
        return QuoteHolder(binding)

    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        holder.binding.pack = pack[position]
        holder.binding.delete.setOnClickListener {
            clicked.remove(position)
        }
        holder.binding.edit.setOnClickListener {
            clicked.edit(position)
        }
    }

    override fun getItemCount(): Int {
        return pack.size
    }

    fun setPACK(pack: MutableList<Package>){
        this.pack = pack
        notifyDataSetChanged()
    }

}

class QuoteHolder(val binding: PackageItemBinding): RecyclerView.ViewHolder(binding.root)



interface EditQuote{

    fun edit(position: Int)
    fun remove(position: Int)
}
