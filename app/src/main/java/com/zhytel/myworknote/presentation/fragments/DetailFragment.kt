package com.zhytel.myworknote.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zhytel.myworknote.R
import com.zhytel.myworknote.databinding.FragmentDeteilBinding
import com.zhytel.myworknote.presentation.DetailViewModel


class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var viewModel: DetailViewModel

    private var _binding: FragmentDeteilBinding? = null
    private val binding: FragmentDeteilBinding
        get() = _binding ?: throw RuntimeException("FragmentDeteilBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeteilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        launchEditMode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_detail, menu)
    }

    private fun launchEditMode(){
        viewModel.getShopItem(args.id)
        viewModel.noteItem.observe(viewLifecycleOwner){
            binding.edTitle.setText(it.title)
            binding.edDescription.setText(it.description)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mn_save -> {
                viewModel.getShopItem(args.id)
                viewModel.editShopItem(
                    binding.edTitle.text?.toString(),
                    binding.edDescription.text?.toString()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

