package com.farmdrop.producers.ui.producer

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Vlad Sabau on 30.03.19.
 */
abstract class PaginationScrollListener
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLastPage()
            && visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition >= 0) {
                loadMoreItems()
        }
    }
    abstract fun loadMoreItems()
}