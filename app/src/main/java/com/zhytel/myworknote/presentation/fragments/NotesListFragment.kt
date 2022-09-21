package com.zhytel.myworknote.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.zhytel.myworknote.databinding.FragmentNotesListBinding
import com.zhytel.myworknote.presentation.NotesListViewModel
import com.zhytel.myworknote.presentation.adapters.NoteListAdapter

class NotesListFragment : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var noteAdapter: NoteListAdapter

    private var _binding: FragmentNotesListBinding? = null
    private val binding: FragmentNotesListBinding
    get() = _binding ?: throw RuntimeException("FragmentNotesListBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[NotesListViewModel::class.java]
        viewModel.noteList.observe(viewLifecycleOwner){
            noteAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvNotesList) {
            noteAdapter = NoteListAdapter()
            adapter = noteAdapter
            recycledViewPool.setMaxRecycledViews(
                NoteListAdapter.VIEW_TYPE_ENABLED,
                NoteListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                NoteListAdapter.VIEW_TYPE_DISABLED,
                NoteListAdapter.MAX_POOL_SIZE
            )
        }
        setupClickListener()
        setupSwipeListener(binding.rvNotesList)
    }
    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noteAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNote(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
    private fun setupClickListener() {
        noteAdapter.onNodeClickListener = {

        }
        binding.buttonAddShopItem.setOnClickListener {
            viewModel.addNote()
        }
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            NotesListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}