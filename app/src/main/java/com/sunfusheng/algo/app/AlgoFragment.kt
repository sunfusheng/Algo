package com.sunfusheng.algo.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangcheng.leetcode.leetCodeDataSource
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter
import com.sunfusheng.StickyHeaderDecoration
import com.sunfusheng.algo.R
import com.sunfusheng.algo.algoDataSource
import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.CodeViewerActivity
import kotlinx.android.synthetic.main.fragment_algo.*

/**
 * @author sunfusheng
 * @since 2020/3/11
 */

const val ALGO = 0
const val LEET_CODE = 1

class AlgoFragment : Fragment() {

    companion object {
        fun getInstance(from: Int): AlgoFragment {
            val fragment = AlgoFragment()
            fragment.arguments = bundleOf("from" to from)
            return fragment
        }
    }

    private var mFrom = ALGO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFrom = arguments?.getInt("from") ?: ALGO
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_algo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataSource()
    }

    private fun loadDataSource() {
        vRecyclerView.layoutManager = LinearLayoutManager(context)
        vRecyclerView.addItemDecoration(StickyHeaderDecoration())
        val groupAdapter =
            StickyGroupAdapter(
                context,
                if (mFrom == ALGO) algoDataSource else leetCodeDataSource
            )
        vRecyclerView.adapter = groupAdapter

        groupAdapter.setOnItemClickListener { _, item, _, _ ->
            if (item.className != null) {
                val intent = Intent(context, CodeViewerActivity::class.java)
                intent.putExtra(CodeViewerActivity.PARAM_KEY, item)
                startActivity(intent)
            }
        }
    }
}

class StickyGroupAdapter(context: Context?, groups: List<List<AlgoItem>>) :
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