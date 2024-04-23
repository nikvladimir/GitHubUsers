package com.alfadroid.githubusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfadroid.githubusers.databinding.RecyclerViewUserItemBinding
import com.alfadroid.githubusers.retrofit.UserInListDto

class UsersAdapter(
    private val dataSet: List<UserInListDto>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerViewUserItemBinding.bind(itemView)

        fun bind(gitHubUserData: UserInListDto, position: Int) = with(binding) {
            tvUserLogin.text = gitHubUserData.login
            tvUserId.text = gitHubUserData.id.toString()
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
