package com.blacksmith.scootsysample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()


    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)

    }

    override fun getCount(): Int {
        return mFragmentList.size
    }


    fun addFragment(fragment: Fragment) {
        try {
            mFragmentList.add(fragment)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}