package com.grand.kotlinnews.ui.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.grand.kotlinnews.databinding.FragmentKotlinNewsBinding

class NewsFragment : Fragment() {
    private lateinit var binding:FragmentKotlinNewsBinding
    private val args : NewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKotlinNewsBinding.inflate(inflater, container, false)
        binding.articalTitle.text=args.text
        binding.toolbar2.title=args.title
        val layoutParams = LinearLayout.LayoutParams(0, 0)
        if(args.url == "null")
        {
            binding.articalImageview.layoutParams = layoutParams
        }
        else {
            Glide.with(requireContext()).load(args.url)
                .into(binding.articalImageview)
        }
        return binding.root
    }
}