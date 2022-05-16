package com.example.transactionstestapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Transaction
import com.example.transactionstestapp.databinding.ItemTransactionBinding
import java.util.*

class TransactionsAdapter: ListAdapter<Transaction, RecyclerView.ViewHolder>(UI_MODEL_COMPARATOR){
    companion object {
        private const val ITEM_VIEW_TYPE_ITEM = -1
        private val UI_MODEL_COMPARATOR = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> TransactionViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionViewHolder -> {
                val item = getItem(position) as Transaction
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            else -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TransactionViewHolder(private val binding: ItemTransactionBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(transaction: Transaction){
                binding.transactionTypeImg.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context,
                        if(transaction.amount > 0)
                            R.drawable.bg_green_circle
                        else
                            R.drawable.bg_red_circle)
                )
                //TODO: create an utils file or an extension to pass the currency locale/name by parameter
                binding.transactionAmount.text = "${transaction.getDisplayAmount()} ${Currency.getInstance("EUR").symbol}"

                binding.transactionDescription.text = transaction.description

                transaction.getDateObject()?.let {
                    binding.transactionDate.text = transaction.getFormattedDate()
                }
            }

        companion object {
            fun from(parent: ViewGroup): TransactionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTransactionBinding.inflate(layoutInflater, parent, false)
                return TransactionViewHolder(binding)
            }
        }
        }
}