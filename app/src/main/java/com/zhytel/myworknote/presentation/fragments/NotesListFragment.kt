package com.zhytel.myworknote.presentation.fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zhytel.myworknote.R
import com.zhytel.myworknote.databinding.FragmentNotesListBinding
import com.zhytel.myworknote.domain.entity.Note
import com.zhytel.myworknote.presentation.NotesListViewModel
import com.zhytel.myworknote.presentation.adapters.NoteListAdapter

class NotesListFragment : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var noteAdapter: NoteListAdapter

    private var _binding: FragmentNotesListBinding? = null
    private val binding: FragmentNotesListBinding
        get() = _binding ?: throw RuntimeException("FragmentNotesListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        delayProgressbar()
        viewModel = ViewModelProvider(this)[NotesListViewModel::class.java]
        viewModel.noteList.observe(viewLifecycleOwner) {
            checkingData(it)
        }

    }

    private fun checkingData(note: List<Note>) {
        if (hasConnection(viewModel.getApplication())) {
            if (note.isNotEmpty()) {
                note.forEach {
                    viewModel.updateTimeInNote(it)
                }
                binding.progressHorizontalServer.max = note.size
                binding.progressHorizontalServer.progress = note.lastIndex
                noteAdapter.submitList(note)
                binding.textInternet.isVisible = false
                binding.textSize.isVisible = false
            } else {
                binding.textSize.isVisible = true
            }
        } else {
            Snackbar.make(binding.root, R.string.st_internet, Snackbar.LENGTH_LONG).show()
            binding.textInternet.isVisible = true
            binding.progressHorizontalServer.progress = 0
        }
    }

    private fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }

    private fun delayProgressbar() {
        val oneMin = 1000
        object : CountDownTimer(
            oneMin.toLong(), 100
        ) {
            override fun onTick(millisUntilFinished: Long) {
                val finishedSeconds = oneMin - millisUntilFinished
                val total = (finishedSeconds.toFloat() / oneMin.toFloat() * 50.0).toInt()
                binding.progressServer.progress = total
            }

            override fun onFinish() {
                binding.progressServer.isVisible = false
            }
        }.start()
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
            launchFragment(it.id)
        }
        binding.buttonAddShopItem.setOnClickListener {
            viewModel.addNote()
        }
    }

    private fun launchFragment(noteId: Int) {
        findNavController().navigate(
            NotesListFragmentDirections.actionNotesListFragmentToDetailFragment(noteId)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}