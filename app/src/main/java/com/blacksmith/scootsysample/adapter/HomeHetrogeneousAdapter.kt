package com.blacksmith.scootsysample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blacksmith.scootsysample.R
import com.blacksmith.scootsysample.activity.MainActivity
import com.blacksmith.scootsysample.data.model.Category
import com.blacksmith.scootsysample.data.model.VendorTypeDetails
import com.blacksmith.scootsysample.databinding.LayoutHeaderBinding
import com.blacksmith.scootsysample.databinding.LayoutItemBannerBinding
import com.blacksmith.scootsysample.databinding.LayoutItemRecylerviewBinding
import com.blacksmith.scootsysample.fragment.ScreenSlideFragment
import com.blacksmith.scootsysample.repository.model.HetroModel
import com.bumptech.glide.Glide

class HomeHetrogeneousAdapter(val activity: MainActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER: Int = 1
    private val TYPE_CATEGORY: Int = 2
    private val TYPE_BANNER: Int = 3
    var mDataList = ArrayList<HetroModel>()


    override fun getItemCount(): Int {

        return mDataList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderHolder -> {
                setHeaderViews(holder, position)
            }
            is CategoryHolder -> {
                setCategoryViews(holder, position)
            }
            is BannerHolder -> {
                setBannerViews(holder, position)
            }

        }
    }

    private fun setBannerViews(holder: BannerHolder, position: Int) {
        val mUrl = mDataList.get(position).mDataList.get(0)
        if (mUrl is String) {
            Glide.with(activity.applicationContext).load(mUrl)
                .into(holder.binding.layItemBannerImgDisplay)
        }
    }

    private fun setCategoryViews(holder: CategoryHolder, position: Int) {
        holder.binding.layItemRecylerview.layoutManager =
            GridLayoutManager(activity.applicationContext, 3)
        val mAdapter = GridCategoryAdapter(activity.applicationContext)
        holder.binding.layItemRecylerview.adapter = mAdapter

        mAdapter.setData(mDataList.get(1).mDataList as ArrayList<Category>)


    }


    private fun setHeaderViews(holder: HeaderHolder, position: Int) {


        val pagerAdapter = ScreenSlidePagerAdapter(activity.supportFragmentManager)

        mDataList.get(0).mDataList.forEach {
            when (it) {
                is VendorTypeDetails -> {
                    pagerAdapter.addFragment(ScreenSlideFragment.newInstance(it.image))
                }
            }
        }

        holder.binding.layHeaderViewpager.adapter = pagerAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TYPE_HEADER -> {
                val binding = DataBindingUtil.inflate<LayoutHeaderBinding>(
                    layoutInflater,
                    R.layout.layout_header,
                    parent,
                    false
                )
                return HeaderHolder(binding)
            }
            TYPE_CATEGORY -> {
                val binding = DataBindingUtil.inflate<LayoutItemRecylerviewBinding>(
                    layoutInflater,
                    R.layout.layout_item_recylerview,
                    parent,
                    false
                )
                return CategoryHolder(binding)
            }
            TYPE_BANNER -> {
                val binding = DataBindingUtil.inflate<LayoutItemBannerBinding>(
                    layoutInflater,
                    R.layout.layout_item_banner,
                    parent,
                    false
                )
                return BannerHolder(binding)
            }

        }

        val binding = DataBindingUtil.inflate<LayoutItemRecylerviewBinding>(
            layoutInflater,
            R.layout.layout_item_recylerview,
            parent,
            false
        )
        return CategoryHolder(binding)


    }

    fun setData(mDataList: ArrayList<HetroModel>) {
        this.mDataList = mDataList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        when (mDataList.get(position).type) {
            1 -> return TYPE_HEADER
            2 -> return TYPE_CATEGORY
            3 -> return TYPE_BANNER
        }
        return 2
    }

    class HeaderHolder(val binding: LayoutHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    class CategoryHolder(val binding: LayoutItemRecylerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    class BannerHolder(val binding: LayoutItemBannerBinding) : RecyclerView.ViewHolder(binding.root)
}