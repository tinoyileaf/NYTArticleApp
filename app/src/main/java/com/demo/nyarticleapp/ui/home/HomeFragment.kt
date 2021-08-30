package com.demo.nyarticleapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.nyarticleapp.R
import com.demo.nyarticleapp.databinding.FragmentHomeBinding
import com.demo.nyarticleapp.ui.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: MainActivityViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var rvArticlesAdapter: RVArticlesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpViews()
        setUpObservers()
        viewModel.getArticles()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpViews() {
        binding.rvArticles.layoutManager = LinearLayoutManager(context)
        rvArticlesAdapter = RVArticlesAdapter(context)
        binding.rvArticles.adapter = rvArticlesAdapter
    }

    private fun setUpObservers() {
        viewModel.progress.observe(viewLifecycleOwner, {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })
        viewModel.articleList.observe(viewLifecycleOwner, {
            it?.let {
                rvArticlesAdapter.updateList(it)
                rvArticlesAdapter.notifyDataSetChanged()
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Snackbar.make(binding.root, it?: getString(R.string.something_wrong), Snackbar.LENGTH_LONG).show()
        })
    }
}