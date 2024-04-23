package com.alfadroid.githubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.githubusers.databinding.RecyclerViewUserItemBinding

class UsersAdapter(
    private val dataSet: Array<String>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerViewUserItemBinding.bind(itemView)

        fun bind(userLogin: String, position: Int) = with(binding) {
            tvUserId.text = userLogin
            tvUserLogin.text = userLogin
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_user_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position], position)
    }

    override fun getItemCount() = dataSet.size
}
