package com.sunfusheng.algo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunfusheng.FirUpdater
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter
import com.sunfusheng.StickyHeaderDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkUpdate()
        loadDataSource()
    }

    private fun checkUpdate() {
        FirUpdater.getInstance(applicationContext)
            .apiToken("3c57fb226edf7facf821501e4eba08d2")
            .appId("5e50fd3923389f1297a7aa5e")
            .checkVersion()
    }

    private fun loadDataSource() {
        vRecyclerView.layoutManager = LinearLayoutManager(this)
        vRecyclerView.addItemDecoration(StickyHeaderDecoration())
        val groupAdapter = StickyGroupAdapter(this, getDataSource())
        vRecyclerView.adapter = groupAdapter

        groupAdapter.setOnItemClickListener { adapter, item, groupPosition, childPosition ->
            if (item.className != null) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PARAM_KEY, item)
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

        holder.setText(R.id.vHeader, item.category.second)
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