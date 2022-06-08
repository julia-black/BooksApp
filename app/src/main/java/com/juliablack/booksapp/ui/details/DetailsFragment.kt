package com.juliablack.booksapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.juliablack.booksapp.R
import com.juliablack.booksapp.databinding.FragmentDetailsBinding
import com.juliablack.booksapp.util.displayImage
import com.juliablack.domain.model.BookDetails
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel { parametersOf(url) }

    private val url by lazy { arguments?.get(ARG_URL) as String }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        with(viewModel) {
            liveBook.observe(viewLifecycleOwner) {
                showDetails(it)
            }
            liveError.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showDetails(bookDetails: BookDetails) {
        with(binding) {
            title.text = bookDetails.title
            author.text = bookDetails.author
            price.text = getString(R.string.price, bookDetails.price)
            image.displayImage(requireContext(), bookDetails.image)
        }
    }

    companion object {
        private const val ARG_URL = "arg_url"

        fun newInstance(url: String) = DetailsFragment().apply {
            arguments = bundleOf(ARG_URL to url)
        }
    }
}