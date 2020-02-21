package com.sunfusheng.algo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter
import com.sunfusheng.StickyHeaderDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vRecyclerView.layoutManager = LinearLayoutManager(this)
        vRecyclerView.addItemDecoration(StickyHeaderDecoration())
        val groupAdapter = StickyGroupAdapter(this, getDataSource())
        vRecyclerView.adapter = groupAdapter
        groupAdapter.setOnItemClickListener { adapter, item, groupPosition, childPosition ->
            Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
        }
    }
}

class StickyGroupAdapter(context: Context, groups: List<List<String?>>) :
    HeaderGroupRecyclerViewAdapter<String>(context, groups) {

    override fun getHeaderLayoutId(viewType: Int): Int {
        return R.layout.layout_group_header
    }

    override fun getChildLayoutId(viewType: Int): Int {
        return R.layout.layout_group_child
    }

    override fun onBindHeaderViewHolder(
        holder: GroupViewHolder,
        item: String?,
        groupPosition: Int
    ) {

        holder.setText(R.id.vHeader, item)
    }

    override fun onBindChildViewHolder(
        holder: GroupViewHolder,
        item: String?,
        groupPosition: Int,
        childPosition: Int
    ) {
        holder.setText(R.id.vChild, item)
        holder.setVisible(R.id.vDivider, !isGroupLastItem(groupPosition, childPosition))
    }
}