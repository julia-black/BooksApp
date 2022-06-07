package com.juliablack.booksapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliablack.booksapp.R
import com.juliablack.booksapp.databinding.FragmentListBinding
import com.juliablack.booksapp.ui.creating.CreateFragment
import com.juliablack.booksapp.ui.details.DetailsFragment
import com.juliablack.booksapp.ui.list.view.BooksAdapter
import com.juliablack.domain.model.Book
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.createButton.setOnClickListener {
            toCreate()
        }
    }

    private fun setObservers() {
        with(binding) {
            viewModel.liveBooks.observe(viewLifecycleOwner) {
                list.adapter = BooksAdapter(it) { item ->
                    toDetails(item)
                }
                list.layoutManager = LinearLayoutManager(context)
            }
            viewModel.liveError.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun toDetails(book: Book) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(book.link))
            .addToBackStack(null)
            .commit()
    }

    private fun toCreate() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CreateFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}