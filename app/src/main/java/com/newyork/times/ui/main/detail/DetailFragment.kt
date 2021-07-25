package com.newyork.times.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.newyork.times.R


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedArticle =
            DetailFragmentArgs.fromBundle(requireArguments()).selectedArticle
        val webView: WebView = view.findViewById(R.id.web_view_article)
        webView.loadUrl(selectedArticle.url)
    }


}