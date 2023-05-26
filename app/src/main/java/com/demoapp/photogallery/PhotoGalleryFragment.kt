package com.demoapp.photogallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.demoapp.photogallery.databinding.FragmentPhotoGalleryBinding


class PhotoGalleryFragment : Fragment() {

    private lateinit var binding: FragmentPhotoGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoGalleryBinding.inflate(
            inflater,
            container,
            false
        )

        binding.photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return binding.root
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}