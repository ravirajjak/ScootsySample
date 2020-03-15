package com.blacksmith.scootsysample.activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blacksmith.scootsysample.R
import com.blacksmith.scootsysample.adapter.HomeHetrogeneousAdapter
import com.blacksmith.scootsysample.data.model.Header
import com.blacksmith.scootsysample.databinding.ActivityMainBinding
import com.blacksmith.scootsysample.repository.`interface`.HomeDataService
import com.blacksmith.scootsysample.repository.model.HetroModel
import com.blacksmith.scootsysample.utils.AppConst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    private lateinit var mAdapter: HomeHetrogeneousAdapter
    lateinit var binding: ActivityMainBinding
    private var disposable: Disposable? = null
    lateinit var mDataList: ArrayList<HetroModel>

    companion object {
        fun newInstance(): Class<MainActivity> {
            return MainActivity::class.java
        }
    }


    private val homeDataService by lazy {
        HomeDataService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        init()
    }

    private fun init() {
        setAdapter()
        getData()

    }

    private fun setAdapter() {
        mAdapter = HomeHetrogeneousAdapter(this)
    }

    private fun getData() {
        disposable = homeDataService.getHomeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    onSuccess(result, AppConst.MENU)
                },
                { error -> error.message?.let { showToast(it) } }
            )
    }

    private fun getBannerData() {
        disposable = homeDataService.getHomeBannerData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    onSuccess(result, AppConst.BANNER)
                },
                { error -> error.message?.let { showToast(it) } }
            )
    }


    private fun onSuccess(result: Header, type: String) {
        if (type.equals(AppConst.MENU)) {
            if (result.response.code == 200) {
                mDataList = ArrayList<HetroModel>()
                getBannerData()
//            Log.d("RESULT", Gson().toJson(result))
                binding.actMainRecylerview.layoutManager = LinearLayoutManager(this)

                binding.actMainRecylerview.adapter = mAdapter


                result.main_content.data.forEach {


                    if (it.vendor_type_details != null) {
                        val mTempHeader = HetroModel(it.vendor_type_details, 1)
                        mDataList.add(mTempHeader)
                    }
                }
                result.main_content.data.forEach {
                    if (it.category != null) {
                        val mTempCategory = HetroModel(it.category, 2)

                        mDataList.add(mTempCategory)
                    }
                }

                mAdapter.setData(mDataList)

            } else {
                showToast("Something went wrong")
            }
        } else if (type.equals(AppConst.BANNER)) {
            if (result.response.code == 200) {
                val mList = ArrayList<String>()
                result.main_content.item.data.forEach {
                    mList.add(it.image)
                    val mTempHeader = HetroModel(mList, 3)
                    mDataList.add(mTempHeader)

                }

                mAdapter.setData(mDataList)

            }
        }
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
