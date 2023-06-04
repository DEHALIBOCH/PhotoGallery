package com.demoapp.photogallery

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.demoapp.photogallery.databinding.FragmentPhotoPageBinding

private const val ARG_URI = "photo_page_url"

class PhotoPageFragment : VisibleFragment() {
    private lateinit var uri: Uri
    private lateinit var webView: WebView
    private lateinit var binding: FragmentPhotoPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uri = arguments?.getParcelable(ARG_URI) ?: Uri.EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance(uri: Uri?): PhotoPageFragment {
            return PhotoPageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_URI, uri)
                }
            }
        }
    }
}