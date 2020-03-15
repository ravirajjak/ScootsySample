package com.blacksmith.scootsysample.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.blacksmith.scootsysample.R
import com.blacksmith.scootsysample.databinding.FragmentScreenSlideBinding
import com.blacksmith.scootsysample.utils.AppConst
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class ScreenSlideFragment : Fragment() {

    lateinit var binding: FragmentScreenSlideBinding
    lateinit var mStrUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleData()
    }

    private fun getBundleData() {
        arguments.let {
            mStrUrl = it?.getString(AppConst.PUT_EXT_IMG_URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_screen_slide, container, false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        if (mStrUrl.isNotEmpty())
            Glide.with(this).load(mStrUrl).into(binding.layItemCardviewImgDisplay)
    }

    companion object {
        fun newInstance(
            mStrUrl: String
        ) =
            ScreenSlideFragment().apply {
                arguments = Bundle().apply {
                    putString(AppConst.PUT_EXT_IMG_URL, mStrUrl)


                }
            }


    }
}
