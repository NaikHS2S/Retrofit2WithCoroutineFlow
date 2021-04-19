package com.example.dev.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dev.adapters.DataAdapter
import com.example.dev.databinding.HomeBinding
import com.example.dev.model.Joke
import com.example.dev.viewmodel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: HomeBinding? = null
    private val viewModel: JokesViewModel by viewModels()

    private var adapter: DataAdapter? = null
    private val jokeList: ArrayList<Joke>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        setUpItemTouchHelper()
        viewModel.jokesFromApi
    }

    private fun setUpItemTouchHelper() {
        val simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val joke = adapter?.getJokeAt(position)
                adapter?.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerView)
    }

    private fun observeData() {
        viewModel.jokeSMutableList.observe(viewLifecycleOwner, {
            Log.e(TAG, "onChanged: " + it.size)
            adapter?.updateList(it)
        })
    }

    private fun initRecyclerView() {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = DataAdapter(requireContext(), jokeList)
        binding?.recyclerView?.adapter = adapter
    }

    companion object {
        private const val TAG = "Home Fragment"
    }
}