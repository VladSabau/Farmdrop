package com.farmdrop.producers.ui.producer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.farmdrop.producers.R
import com.farmdrop.producers.databinding.ActivityProducersBinding
import com.farmdrop.producers.di.ViewModelFactory

class ProducersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProducersBinding
    private lateinit var viewModel: ProducersViewModel
    private var errorSnackbar: Snackbar? = null

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, com.farmdrop.producers.R.layout.activity_producers)
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.producersRecyclerView.layoutManager = layout

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(ProducersViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        setPagination(layout)
    }

    private fun setPagination(layout: LinearLayoutManager) {
        binding.producersRecyclerView.addOnScrollListener(object : PaginationScrollListener(layout) {

            override fun loadMoreItems() {
                currentPage += 1
                if (checkInternetConnection())
                    viewModel.loadProducers(currentPage, 10)
            }

            //todo: add logic for last page
            override fun isLastPage(): Boolean {
                return false
            }
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}