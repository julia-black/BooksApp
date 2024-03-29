package com.juliablack.booksapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliablack.booksapp.R
import com.juliablack.booksapp.databinding.FragmentListBinding
import com.juliablack.booksapp.ui.creating.CreateFragment
import com.juliablack.booksapp.ui.details.DetailsFragment
import com.juliablack.booksapp.ui.list.view.BooksAdapter
import com.juliablack.domain.model.Book
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BooksAdapter

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
        setViews()
        setObservers()
        setListeners()
    }

    private fun setViews() {
        with(binding.list) {
            bookAdapter = BooksAdapter { toDetails(it) }
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setListeners() {
        with(binding) {
            createButton.setOnClickListener {
                toCreate()
            }
        }
    }

    private fun setObservers() {
        viewModel.liveBooks.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                bookAdapter.submitData(it)
            }
        }
        viewModel.liveError.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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