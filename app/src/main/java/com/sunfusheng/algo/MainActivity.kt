package com.sunfusheng.algo

import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.sunfusheng.FirUpdater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAB_NAMES = intArrayOf(R.string.tab_algo, R.string.tab_leetcode)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkUpdate()
        loadFragments()
    }

    private fun checkUpdate() {
        FirUpdater.getInstance(applicationContext)
            .apiToken("3c57fb226edf7facf821501e4eba08d2")
            .appId("5e50fd3923389f1297a7aa5e")
            .checkVersion()
    }

    private fun loadFragments() {
        val fragments = SparseArray<Fragment>()
        fragments.put(ALGO, AlgoFragment.getInstance(ALGO))
        fragments.put(LEET_CODE, AlgoFragment.getInstance(LEET_CODE))

        val adapter = FragmentViewPager2Adapter(this)
        adapter.fragments = fragments
        vViewPager.adapter = adapter
        TabLayoutMediator(vTabLayout, vViewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.setText(
                    TAB_NAMES[position]
                )
            }
        ).attach()
    }
}

class FragmentViewPager2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    lateinit var fragments: SparseArray<Fragment>

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }
}