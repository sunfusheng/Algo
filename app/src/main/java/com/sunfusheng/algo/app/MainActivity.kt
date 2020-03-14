package com.sunfusheng.algo.app

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.sunfusheng.FirUpdater
import com.sunfusheng.algo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAB_NAMES = intArrayOf(
        R.string.tab_algo,
        R.string.tab_leetcode
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(vToolbar)
        title = ""

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
        fragments.put(0, AlgoFragment.getInstance(FromAlgo))
        fragments.put(1, AlgoFragment.getInstance(FromLeetCode))

        val adapter = FragmentViewPager2Adapter(this, fragments)
        vViewPager.adapter = adapter
        TabLayoutMediator(vTabLayout, vViewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.setText(TAB_NAMES[position])
            }
        ).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

class FragmentViewPager2Adapter(activity: FragmentActivity, val fragments: SparseArray<Fragment>) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }
}