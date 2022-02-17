package com.example.testapp.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.testapp.R
import com.example.testapp.data.ResponseData
import com.example.testapp.viewmodel.MediaViewModel
import com.example.testapp.viewmodel.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_media.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaFragment : Fragment(), ViewModelProvider<MediaViewModel> {

    private var currentItemIndex = 0
    private var ids: List<Int>? = null
    private var content: ResponseData? = null

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
        initViews()
        val data = viewModel().getIds()
        data.observe(
            viewLifecycleOwner,
            {
                ids = it
                initCurrentContext(it)
            })
    }

    private fun initViews() {
        wvContent.webViewClient = WebViewClient()
        wvContent.webChromeClient = WebChromeClient()
        wvContent.settings.javaScriptEnabled = true

        fabPrev.setOnClickListener {
            ids?.let {
                when (currentItemIndex) {
                    0 -> currentItemIndex = it.size - 1
                    else -> currentItemIndex--
                }
                initCurrentContext(it)
            }
        }

        fabNext.setOnClickListener {
            ids?.let {
                when (currentItemIndex) {
                    it.size - 1 -> currentItemIndex = 0
                    else -> currentItemIndex++
                }
                initCurrentContext(it)
            }
        }
    }

    private fun initCurrentContext(ids: List<Int>) {
        val context = viewModel().getContext(ids[currentItemIndex])
        context.observe(
            viewLifecycleOwner,
            {
                when (it.type) {
                    "text" -> {
                        tvContent.visibility = View.VISIBLE
                        wvContent.visibility = View.GONE
                        it.payload.text?.let { text ->
                            tvContent.text = text
                        }
                    }
                    "webpage" -> {
                        wvContent.visibility = View.VISIBLE
                        tvContent.visibility = View.GONE
                        it.payload.url?.let { url ->
                            wvContent.loadUrl(url)
                        }
                    }
                }
            })
    }
}