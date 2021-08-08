package com.example.backgroundwork.view.users.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.backgroundwork.R
import com.example.backgroundwork.databinding.UserItemBinding
import com.example.backgroundwork.models.presentation.UserModel
import com.example.backgroundwork.view.users.fragment.UsersAdapter.UserModelViewHolder

class UsersAdapter : RecyclerView.Adapter<UserModelViewHolder>() {

    private var users: List<UserModel> = emptyList()

    fun submitItems(newUsers: List<UserModel>) {
        users = newUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserModelViewHolder =
        UserModelViewHolder.create(parent)

    override fun onBindViewHolder(holder: UserModelViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    override fun getItemCount(): Int = users.size

    class UserModelViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding: UserItemBinding = UserItemBinding.bind(itemView)

        fun bindView(userModel: UserModel) {
            binding.apply {
                with(userModel) {
                    appNameTextView.text = login
                    appIconImageView.load(avatarUrl)
                }
            }
        }

        companion object {
            fun create(container: ViewGroup) = UserModelViewHolder(
                LayoutInflater.from(container.context).inflate(R.layout.user_item, container, false)
            )
        }
    }

}