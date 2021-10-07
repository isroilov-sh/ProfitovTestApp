package com.example.moviestestapp.ui.media

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestestapp.R
import com.example.moviestestapp.data.ResponseData
import com.example.moviestestapp.viewmodel.MediaViewModel
import com.example.moviestestapp.viewmodel.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_media.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaFragment : Fragment(), ViewModelProvider<MediaViewModel> {
    override fun viewModel(): MediaViewModel {
        val viewModel: MediaViewModel by viewModel<MediaViewModel>()
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_media, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = viewModel().getMediaFromRepository()
        data.observe(
            viewLifecycleOwner,
            { initRecycler(it) })

    }

    private fun initRecycler(responseData: ResponseData) {
        val mediaAdapter = MediaAdapter(responseData.results)
        mediaRecyclerView.apply {
            adapter = mediaAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}