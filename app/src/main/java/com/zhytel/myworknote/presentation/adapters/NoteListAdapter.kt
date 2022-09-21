package com.zhytel.myworknote.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.zhytel.myworknote.databinding.NoteItemBinding
import com.zhytel.myworknote.domain.entity.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter: ListAdapter<Note, NoteViewHolder>(NoteDiffCallback) {

    var onNodeClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.itemView
        with(holder.binding){
            with(note){
//                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                tvTitle.text = title
                tvDescription.text = description
//                val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                tvTime.text = time
                root.setOnClickListener {
                    onNodeClickListener?.invoke(this)
                }
            }
        }
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 30
    }
}