package com.zxc.roomkotlin

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zxc.roomkotlin.databinding.GridItemBinding

class GridAdapter(private var mItems: List<GridItem>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: GridItemBinding = DataBindingUtil.inflate(inflater, R.layout.grid_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = mItems[position]
        holder.binding.listener = clickListener
    }

    private val clickListener = object : ClickListener {
        override fun onClick(o: Any) {
            if (o is GridItem) {
                for (mItem in mItems) {
                    if (mItem.id == o.id) {
                        mItem.isChecked = !mItem.isChecked
                    }
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ViewHolder(val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root)
}
