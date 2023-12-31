package com.example.notesappassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notesappassignment.MVVM.SlideViewModel
import com.example.notesappassignment.databinding.FragmentSlideShowBinding


class SlideShowFragment : Fragment() {
    private var _binding:FragmentSlideShowBinding?=null
    private val binding get()=_binding!!
    private lateinit var slideModel:SlideViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSlideShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideModel=ViewModelProvider(this).get(SlideViewModel::class.java)
    }


}