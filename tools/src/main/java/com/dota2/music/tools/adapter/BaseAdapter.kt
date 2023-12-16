package com.dota2.music.tools.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, B: ViewDataBinding>(val mContext: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mList: List<T>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate =
            DataBindingUtil.inflate<B>(LayoutInflater.from(mContext), getLayoutId(), parent, false)

        return BaseViewHolder(inflate.root)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        binding?.let {
            onBindItem(binding, mList?.get(position), holder as BaseViewHolder)
            it.executePendingBindings()
        }
    }

    abstract fun onBindItem(binding: B?, item: T?, holder: BaseViewHolder)

    abstract fun getLayoutId(): Int

    class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}