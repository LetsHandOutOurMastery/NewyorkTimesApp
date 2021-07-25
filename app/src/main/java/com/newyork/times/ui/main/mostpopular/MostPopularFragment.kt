package com.newyork.times.ui.main.mostpopular

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newyork.times.R
import com.newyork.times.databinding.FragmentMostPopularBinding
import com.newyork.times.model.Article
import com.newyork.times.ui.main.adapter.MostPopularAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularFragment : Fragment() {

    lateinit var binding: FragmentMostPopularBinding
    lateinit var mostPopularAdapter: MostPopularAdapter
    private val mostPopularViewModel: MostPopularViewModel by activityViewModels()

    companion object {
        fun newInstance() = MostPopularFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMostPopularBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.mostPopularViewModel = mostPopularViewModel
        // configure recycler view
        configureRecyclerView(binding.rvArticleList)

        // register observers
        mostPopularViewModel.navigateToArticleDetail.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    MostPopularFragmentDirections.actionArticleListFragmentToDetailFragment(it)
                )
                mostPopularViewModel.doneNavigationToDetail()
            }
        }
        mostPopularViewModel.networkErrorState.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.rvArticleList.visibility = View.GONE
                    binding.llNetworkError.visibility = View.VISIBLE
                } else {
                    binding.rvArticleList.visibility = View.VISIBLE
                    binding.llNetworkError.visibility = View.GONE
                }
                mostPopularViewModel.resetNetworkErrorStatus()
            }
        }
        mostPopularViewModel.articleList().observe(viewLifecycleOwner) {
            mostPopularAdapter.submitList(it)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)
        val searchView = SearchView(requireContext())
        menu.findItem(R.id.search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                var searchText = newText
                searchText = "%$searchText%"
                mostPopularViewModel.articleList(searchText)
                    .observe(viewLifecycleOwner) {
                        it?.let { mostPopularAdapter.submitList(it) }
                    }
                return false
            }
        })
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    private fun configureRecyclerView(recyclerView: RecyclerView) {
        mostPopularAdapter = MostPopularAdapter(object : MostPopularAdapter.OnClickListener {
            override fun onClick(article: Article) {
                mostPopularViewModel.showArticleDetail(article)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mostPopularAdapter
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}