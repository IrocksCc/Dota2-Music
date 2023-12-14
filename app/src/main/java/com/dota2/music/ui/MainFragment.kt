package com.dota2.music.ui

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import cn.bingoogolapple.bgabanner.BGABanner
import com.dota2.music.R
import com.dota2.music.base.BaseFragment
import com.dota2.music.bean.BannerBean
import com.dota2.music.databinding.FragmentMainBinding
import com.dota2.music.viewmodel.MainFragmentViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainFragment : BaseFragment(), BGABanner.Delegate<AppCompatImageView?, String>, BGABanner.Adapter<AppCompatImageView?, String?>{

    private var mFragmentMainDataBinding: FragmentMainBinding? = null
    private var mMainFragmentViewModel: MainFragmentViewModel? = null

    private var bannerList: MutableList<BannerBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 从网络获取Banner，暂不需要
//        loadBanner()
    }

    private fun loadBanner() {
        // banner?.value发生变化
        mMainFragmentViewModel?.getBanner()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        mFragmentMainDataBinding = DataBindingUtil.bind(view)
        mMainFragmentViewModel = getFragmentViewModel(this, MainFragmentViewModel::class.java)
        mFragmentMainDataBinding?.vm = mMainFragmentViewModel
        mFragmentMainDataBinding?.click = ClickProxy()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化banner
//        mMainFragmentViewModel?.banner?.observe(this) {
//            bannerList = it
            initBanner()
//        }
        //初始化适配器


        // 请求recyclerView数据

        // 数据获取到之后填给list
    }

    private fun initBanner() {
        mFragmentMainDataBinding?.banner?.apply {
            setAutoPlayAble(true)
            val views: MutableList<View> = ArrayList()

            // 从网络获取，需要初始化bannerList
//            bannerList?.forEach {

            // 在此暴力添加可以实现图片轮播的效果
            views.add(AppCompatImageView(this.context).apply {
                setBackgroundResource(R.drawable.app_icon)
            })

            views.add(AppCompatImageView(this.context).apply {
                setBackgroundResource(R.drawable.app_icon)
            })
            views.add(AppCompatImageView(this.context).apply {
                setBackgroundResource(R.drawable.app_icon)
            })
//            }

            setAdapter(this@MainFragment)
            setDelegate(this@MainFragment)
            setData(views)
        }

    }

    inner class ClickProxy {
        fun openMenu() {}

        fun search() {

        }

        fun clickBanner() {}
    }
1
    override fun onBannerItemClick(
        banner: BGABanner?,
        itemView: AppCompatImageView?,
        model: String?,
        position: Int
    ) {


    }

    override fun fillBannerItem(
        banner: BGABanner?,
        itemView: AppCompatImageView?,
        model: String?,
        position: Int
    ) {
        itemView?.apply {
            scaleType = ImageView.ScaleType.CENTER_CROP

        }
    }
}