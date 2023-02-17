package com.example.themealsapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentBookmarksBinding


class BookmarksFragment : Fragment() {

    private val binding by lazy {
        FragmentBookmarksBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return binding.root
    }

}