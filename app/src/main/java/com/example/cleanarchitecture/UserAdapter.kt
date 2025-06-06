package com.example.cleanarchitecture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.UserAdapter.ViewHolder
import com.example.cleanarchitecture.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val userList = mutableListOf<User>()

    fun setData(newUserList: MutableList<User>) {
        val diffResult = DiffUtil.calculateDiff(DiffToolUtil(userList, newUserList))
        userList.clear()
        userList.addAll(newUserList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val bind: ItemUserBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(user: User) {
            with(bind) {
                user.let {
                    userNameTextView.text = "USER ID: ${it.id} ${it.name}"
                    emailTextView.text = it.email
                }
            }
        }
    }

    class DiffToolUtil(
        private val oldList: MutableList<User>, private val newList: MutableList<User>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(
            oldItemPosition: Int, newItemPosition: Int
        ): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(
            oldItemPosition: Int, newItemPosition: Int
        ): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
    }
}