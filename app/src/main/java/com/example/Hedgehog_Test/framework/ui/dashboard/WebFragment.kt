package com.example.Hedgehog_Test.framework.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Hedgehog_Test.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private lateinit var webViewModel: WebViewModel
    private var binding: FragmentWebBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webViewModel =
            ViewModelProvider(this).get(WebViewModel::class.java)

        binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnBackStackToWebView()
        binding?.webView?.loadUrl("https://www.icndb.com/api/")
    }

    private fun setupOnBackStackToWebView() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding?.webView?.canGoBack() == true) {
                    binding?.webView?.goBack()
                } else {
                    findNavController().navigateUp()
                }
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}