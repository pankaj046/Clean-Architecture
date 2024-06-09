package dev.pankaj.cleanarchitecture.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

fun ViewPager.currentFragment(fm: FragmentManager, pagerId: Int): Fragment? {
    return fm.findFragmentByTag("android:switcher:$pagerId:${currentItem}")
}