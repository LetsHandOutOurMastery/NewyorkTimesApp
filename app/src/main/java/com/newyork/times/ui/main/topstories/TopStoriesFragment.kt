package com.newyork.times.ui.main.topstories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newyork.times.R

class TopStoriesFragment : Fragment() {

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

    private lateinit var viewModel: TopStoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_stories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopStoriesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}