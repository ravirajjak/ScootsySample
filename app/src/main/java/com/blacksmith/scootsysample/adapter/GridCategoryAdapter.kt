package com.blacksmith.scootsysample.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blacksmith.scootsysample.R
import com.blacksmith.scootsysample.data.model.Category
import com.blacksmith.scootsysample.databinding.LayoutItemCardviewBinding
import com.bumptech.glide.Glide


class GridCategoryAdapter(val context: Context) :
    RecyclerView.Adapter<GridCategoryAdapter.MyViewHolder>() {

    lateinit var mDataList: List<Category>;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutItemCardviewBinding>(
            layoutInflater,
            R.layout.layout_item_cardview,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        onBind(holder, position)
    }

    private fun onBind(holder: MyViewHolder, position: Int) {
        val mUrl = mDataList.get(position).image
        if (mUrl.isNotEmpty()) {
            var mWidth = calculateColumn(3)
            mWidth = mWidth - 50
            val rlp = LinearLayout.LayoutParams(
                mWidth,
                mWidth - 50
            )

            holder.binding.layItemCardviewLinParent.layoutParams = rlp
            Log.d("STRING_URL", mUrl);
            holder.binding.layItemCardviewTvTitle.text = mDataList.get(position).name
            Glide.with(context).load(mUrl).into(holder.binding.layItemCardviewImgDisplay)
        }
    }

    private fun calculateColumn(columnWidthDp: Int): Int {

        val displayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return screenWidthDp.toInt()

    }


    override fun getItemCount(): Int {

        return mDataList.size
    }


    fun setData(mDataList: ArrayList<Category>) {
        this.mDataList = mDataList
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: LayoutItemCardviewBinding) :
        RecyclerView.ViewHolder(binding.root)


}
