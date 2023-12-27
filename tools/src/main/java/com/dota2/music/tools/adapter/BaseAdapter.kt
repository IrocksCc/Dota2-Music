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
    protected var mOnItemClickListener: OnItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate =
            DataBindingUtil.inflate<B>(LayoutInflater.from(mContext), getLayoutId(), parent, false)

        val baseViewHolder = BaseViewHolder(inflate.root)
        baseViewHolder.itemView.setOnClickListener {
            val position = baseViewHolder.adapterPosition
            mOnItemClickListener?.onItemClick(it, position)
        }

        return baseViewHolder
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

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        mOnItemClickListener = onItemClickListener
    }

    abstract fun onBindItem(binding: B?, item: T?, holder: BaseViewHolder)

    abstract fun getLayoutId(): Int

    class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener<T> {
        fun onItemClick(item: View, position: Int)
    }
}