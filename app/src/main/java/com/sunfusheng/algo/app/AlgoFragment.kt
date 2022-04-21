package com.sunfusheng.algo.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sunfusheng.GroupViewHolder
import com.sunfusheng.HeaderGroupRecyclerViewAdapter
import com.sunfusheng.StickyHeaderDecoration
import com.sunfusheng.algo.R
import com.sunfusheng.algo.algoDataSource
import com.sunfusheng.algo.common.AlgoItem
import com.sunfusheng.algo.common.CodeViewerActivity
import com.wangcheng.leetcode.leetCodeDataSource
import kotlinx.android.synthetic.main.fragment_algo.*
import java.io.Serializable

/**
 * @author sunfusheng
 * @since 2020/3/11
 */

sealed class AlgoSource : Serializable
object FromAlgo : AlgoSource()
object FromLeetCode : AlgoSource()

class AlgoFragment : Fragment() {

  companion object {
    fun getInstance(from: AlgoSource): AlgoFragment {
      val fragment = AlgoFragment()
      fragment.arguments = bundleOf("from" to from)
      return fragment
    }
  }

  private var mFrom: AlgoSource = FromAlgo

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mFrom = (arguments?.getSerializable("from") ?: FromAlgo) as AlgoSource
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
    vRecyclerView.addItemDecoration(StickyHeaderDecoration())
    val groupAdapter =
      StickyGroupAdapter(
        context,
        if (mFrom is FromAlgo) algoDataSource else leetCodeDataSource
      )
    vRecyclerView.adapter = groupAdapter

    groupAdapter.setOnItemClickListener { _, item, _, _ ->
      if (item.className != null) {
        val subject = item.subject!!
        val filePath = item.getFilePath()
        val hardLevel =
          if (mFrom is FromAlgo) item.getAlgoHardLevel() else item.getLeetCodeHardLevel()
        CodeViewerActivity.goto(context!!, subject, filePath, hardLevel)
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