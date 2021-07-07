package com.github.algamza.itunestrack.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.itunestrack.R
import com.github.algamza.itunestrack.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    @Inject lateinit var adapter : ListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if ( !recyclerView.canScrollVertically(1) ) {
                    viewModel.requestTrack(30, viewModel.content.value!!.size)
                }
            }
        })
        viewModel.content.observe(viewLifecycleOwner) { adapter.setData(it) }
        return binding.root
    }
}