package com.juliablack.booksapp.ui.creating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.juliablack.booksapp.R
import com.juliablack.booksapp.databinding.FragmentCreateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateFragment : Fragment() {

    private val viewModel: CreateViewModel by viewModel()

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(binding) {
            createButton.setOnClickListener {
                viewModel.onClickCreate(
                    titleEditText.text.toString(),
                    authorEditText.text.toString(),
                    priceEditText.text.toString(),
                    imageLinkEditText.text.toString()
                )
            }
        }
    }

    private fun setObservers() {
        with(viewModel) {
            liveComplete.observe(viewLifecycleOwner) {
                Toast.makeText(
                    context, getString(R.string.create_success), Toast.LENGTH_LONG
                ).show()
                back()
            }
            liveError.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun back() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object {
        fun newInstance() = CreateFragment()
    }
}