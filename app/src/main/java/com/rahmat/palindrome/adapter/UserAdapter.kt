package com.rahmat.palindrome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahmat.palindrome.data.remote.response.DataItem
import com.rahmat.palindrome.databinding.UserItemBinding
import com.rahmat.palindrome.ui.thirdScreen.ThirdScreenViewModel

class UserAdapter(private val context: Context, private val viewModel: ThirdScreenViewModel): PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if(data!=null){
            holder.bind(data)
            holder.itemView.setOnClickListener {
                viewModel.setPrefernce("${data.firstName} ${data.lastName}", context)

                Toast.makeText(context, "${viewModel.getPreference(context).value} Selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class MyViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem){
            binding.tvName.text= data.firstName
            binding.lastname.text = data.lastName
            binding.emailTextView.text = data.email
            Glide.with(binding.avatarImageView.context).load("https://reqres.in/img/faces/${data.id}-image.jpg").into(binding.avatarImageView)

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

        }

    }
}
