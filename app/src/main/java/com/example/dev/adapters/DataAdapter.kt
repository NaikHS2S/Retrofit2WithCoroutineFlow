package com.example.dev.adapters

import android.content.Context
import com.example.dev.model.Joke
import androidx.recyclerview.widget.RecyclerView
import com.example.dev.adapters.DataAdapter.UserDataViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.dev.databinding.ListItemBinding
import kotlin.collections.ArrayList

class DataAdapter(private val mContext: Context, private var mList: ArrayList<Joke>?) : RecyclerView.Adapter<UserDataViewHolder>() {
    private var binding: ListItemBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        val inflater = LayoutInflater.from(mContext)
        binding = ListItemBinding.inflate(inflater, parent, false)
        return UserDataViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        val s = "Q: ${mList!![position].setup.trim()}"
        holder.itemBinding.jokeText.text = s
        val s1 = "A: ${mList!![position].punchline.trim()}"
        holder.itemBinding.jokeDesc.text = s1
    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    inner class UserDataViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    fun updateList(updatedList: List<Joke?>) {
        mList = updatedList.filterNotNull() as ArrayList<Joke>

        notifyDataSetChanged()
    }

    fun getJokeAt(position: Int): Joke {
        return mList!![position]
    }
}