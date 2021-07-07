package com.github.algamza.itunestrack.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.github.algamza.itunestrack.R
import com.github.algamza.itunestrack.databinding.FragmentFavoriteBinding
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    @Inject lateinit var adapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.recyclerView.adapter = adapter
        viewModel.favorite.observe(viewLifecycleOwner) { adapter.setData(it) }
        return binding.root
    }
}