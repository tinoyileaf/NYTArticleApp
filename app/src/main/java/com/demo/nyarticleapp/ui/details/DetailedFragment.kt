package com.demo.nyarticleapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.demo.nyarticleapp.R
import com.demo.nyarticleapp.databinding.FragmentDetailsBinding

class DetailedFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val args: DetailedFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.apply {
            tvNewsTitle.text = args.title
            tvContent.text = args.content
            tvNewsDate.text = args.date
            tvAuthor.text = args.author
            context?.let {
                Glide.with(requireContext())
                    .load(args.image)
                    .placeholder(R.drawable.placeholder)
                    .into(ivImage)
            }
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}