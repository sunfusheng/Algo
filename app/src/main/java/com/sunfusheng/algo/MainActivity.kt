package com.sunfusheng.algo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheng.leetcode.LeetCodeAdapter
import com.cheng.leetcode.leetCodeList
import com.google.android.material.tabs.TabLayout
import com.sunfusheng.FirUpdater
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter
import com.sunfusheng.StickyHeaderDecoration
import com.sunfusheng.algo.Algo.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ALGO: Int = 0
        const val LEET_CODE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        checkUpdate()
        loadAlgoDataSource()
    }

    private fun initView() {
        vTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == ALGO) {
                    loadAlgoDataSource()
                } else {
                    loadLeetCode()
                }
            }
        })
        vRecyclerView.layoutManager = LinearLayoutManager(this)
        vRecyclerView.addItemDecoration(StickyHeaderDecoration())
    }

    private fun checkUpdate() {
        FirUpdater.getInstance(applicationContext)
            .apiToken("3c57fb226edf7facf821501e4eba08d2")
            .appId("5e50fd3923389f1297a7aa5e")
            .checkVersion()
    }

    private fun loadAlgoDataSource() {
        val groupAdapter = StickyGroupAdapter(this, lists)
        vRecyclerView.adapter = groupAdapter

        groupAdapter.setOnItemClickListener { _, item, _, _ ->
            if (item.className != null) {
                val intent = Intent(this, CodeViewerActivity::class.java)
                intent.putExtra(CodeViewerActivity.PARAM_KEY, item)
                startActivity(intent)
            }
        }
    }

    private fun loadLeetCode() {
        val groupAdapter = LeetCodeAdapter(this, leetCodeList)
        vRecyclerView.adapter = groupAdapter

        groupAdapter.setOnItemClickListener { _, item, _, _ ->
            if (item.className != null) {
                val intent = Intent(this, CodeViewerActivity::class.java)
                intent.putExtra(CodeViewerActivity.PARAM_KEY, Utils.toItem(item))
                startActivity(intent)
            }
        }
    }
}


class StickyGroupAdapter(context: Context, groups: List<List<AlgoItem>>) :
    HeaderGroupRecyclerViewAdapter<AlgoItem>(context, groups) {

    override fun getHeaderLayoutId(viewType: Int): Int {
        return R.layout.layout_group_header
    }

    override fun getChildLayoutId(viewType: Int): Int {
        return R.layout.layout_group_child
    }

    override fun onBindHeaderViewHolder(
        holder: GroupViewHolder,
        item: AlgoItem,
        groupPosition: Int
    ) {

        holder.setText(R.id.vHeader, item.chapter.second)
    }

    override fun onBindChildViewHolder(
        holder: GroupViewHolder,
        item: AlgoItem,
        groupPosition: Int,
        childPosition: Int
    ) {
        holder.setText(R.id.vChild, item.subject)
        holder.setVisible(R.id.vDivider, !isGroupLastItem(groupPosition, childPosition))
    }
}