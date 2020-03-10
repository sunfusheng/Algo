package com.cheng.leetcode

import android.content.Context
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter

/**
 * @author liwangcheng
 * @date 2020/3/10.
 */
class LeetCodeAdapter (context: Context, groups: List<List<AlgoItem>>) :
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