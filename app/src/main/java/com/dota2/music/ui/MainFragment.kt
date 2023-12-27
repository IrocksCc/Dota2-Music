package com.dota2.music.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import cn.bingoogolapple.bgabanner.BGABanner
import com.dota2.music.R
import com.dota2.music.base.BaseFragment
import com.dota2.music.bean.BannerBean
import com.dota2.music.databinding.FragmentMainBinding
import com.dota2.music.databinding.SuggestSongsItemBinding
import com.dota2.music.tools.adapter.BaseAdapter
import com.dota2.music.tools.adapter.SongsItemAdapter
import com.dota2.music.tools.constans.TypeFaceConstans
import com.dota2.music.tools.util.TypeFaceUtils
import com.dota2.music.viewmodel.MainFragmentViewModel
import com.dota2.music.viewmodel.MusicRequestViewModel
import com.dota2.player.DefaultPlayerManager
import com.dota2.player.bean.DefaultAlbum
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView

class MainFragment : BaseFragment(), BGABanner.Delegate<ImageView?, String>, BGABanner.Adapter<ImageView?, String?>{

    private var mFragmentMainDataBinding: FragmentMainBinding? = null
    private var mMainFragmentViewModel: MainFragmentViewModel? = null
    private var mMusicRequestViewModel: MusicRequestViewModel? = null

    private var bannerList: MutableList<BannerBean>? = null
    private var mSongsItemAdapter: SongsItemAdapter<DefaultAlbum.DefaultMusic?, SuggestSongsItemBinding>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Fresco.initialize(this.context)
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
        mMusicRequestViewModel = getFragmentViewModel(this, MusicRequestViewModel::class.java)
        mFragmentMainDataBinding?.vm = mMainFragmentViewModel
        mFragmentMainDataBinding?.click = ClickProxy()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO observe的行为统一管理
        // 初始化banner
//        mMainFragmentViewModel?.banner?.observe(this) {
//            bannerList = it
            initBanner()
//        }

        // TODO 设置字体 将来抽到TypeFaceUtils
        mFragmentMainDataBinding?.suggestSongs?.typeface = TypeFaceUtils.getTypeFace(this.context, TypeFaceConstans.SHUHEI_FONT)

        //初始化适配器
        mSongsItemAdapter = object : SongsItemAdapter<DefaultAlbum.DefaultMusic?, SuggestSongsItemBinding>(this.context, R.layout.suggest_songs_item) {
            override fun onBindItem(
                binding: SuggestSongsItemBinding?,
                item: DefaultAlbum.DefaultMusic?,
                holder: BaseViewHolder
            ) {



                // 设置歌名
                binding?.suggestSongsName?.text = item?.title
                // 设置歌手名字
                binding?.suggestSongsArtist?.text = item?.artist?.name
                // 设置图片
                binding?.suggestSongsImage?.setBackgroundResource(R.drawable.search)
//                 从网络获取
//                binding?.suggestSongsImage?.context?.let {
//                    Glide.with(it).load(item?.coverImg).into(binding?.suggestSongsImage)
//                }
            }

        }
        mFragmentMainDataBinding?.mainRecy?.adapter = mSongsItemAdapter
        mSongsItemAdapter?.setOnItemClickListener(object : BaseAdapter.OnItemClickListener<DefaultAlbum.DefaultMusic?> {
            override fun onItemClick(item: View, position: Int) {
                Log.e("LDX", "mSongsItemAdapter onItemClick ${position}");
                DefaultPlayerManager.instance.playAudio(position)
            }
        })

        Log.e("LDX", "mMusicRequestViewModel onViewCreate");

        // 数据获取到之后填给list
        mMusicRequestViewModel?.musicData?.observe(this) {
            Log.e("LDX", "mMusicRequestViewModel title: ${it?.musics?.get(0)}");
            mSongsItemAdapter?.mList = it?.musics
        }

        // 请求recyclerView数据
        mMusicRequestViewModel?.requstMusicData()
    }

    private fun initBanner() {
        mFragmentMainDataBinding?.banner?.apply {
            setAutoPlayAble(true)
            val views: MutableList<View> = ArrayList()

            // 从网络获取，需要初始化bannerList
//            bannerList?.forEach {

            // 在此暴力添加可以实现图片轮播的效果
            for(i in 0 .. 2) {
                views.add(SimpleDraweeView(this.context).apply {
                    val roundingParams: RoundingParams = RoundingParams.fromCornersRadius(50f)
                    hierarchy.roundingParams = roundingParams
                    setActualImageResource(R.drawable.pengpai)
                })
            }
//            }

            setAdapter(this@MainFragment)
            setDelegate(this@MainFragment)
            setData(views)
        }
    }

    inner class ClickProxy {
        fun openMenu() {
            mSharedViewModel?.toOpenOrCloseDrawer?.value = true
        }

        fun search() = NavHostFragment.findNavController(this@MainFragment).navigate(R.id.action_mainFragment_to_searchFragment)

        fun clickBanner() {}
    }

    override fun onBannerItemClick(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {


    }

    override fun fillBannerItem(
        banner: BGABanner?,
        itemView: ImageView?,
        model: String?,
        position: Int
    ) {
        itemView?.apply {
            scaleType = ImageView.ScaleType.FIT_CENTER

        }
    }
}