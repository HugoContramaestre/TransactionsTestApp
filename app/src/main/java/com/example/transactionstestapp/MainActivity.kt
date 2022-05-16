package com.example.transactionstestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.transactionstestapp.common.EventObserver
import com.example.transactionstestapp.common.ListState
import com.example.transactionstestapp.databinding.ActivityMainBinding
import com.example.transactionstestapp.extensions.applyDefaultSkeleton
import com.example.transactionstestapp.extensions.toggle
import com.example.transactionstestapp.extensions.visible
import com.faltenreich.skeletonlayout.Skeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    private val adapter = TransactionsAdapter()
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        initAdapter()
    }

    private fun initListeners(){
        binding.includeRetry.retryButton.setOnClickListener {
            viewModel.getList()
        }
    }

    private fun initObservers() {
        viewModel.listState.observe(this, EventObserver {
            skeleton.toggle(it is ListState.Loading)
            binding.list.visible(it is ListState.Done)
            binding.list.suppressLayout(it is ListState.Loading)
            binding.includeEmptyList.mainLayout.visible(it is ListState.Empty)
            binding.includeRetry.mainLayout.visible(it is ListState.Error)
        })

        viewModel.items.observe(this, EventObserver {
            adapter.submitList(it)
        })
    }

    private fun initAdapter() {
        binding.list.adapter = adapter
        skeleton = binding.skeletonList.applyDefaultSkeleton(R.layout.item_transaction)
    }
}