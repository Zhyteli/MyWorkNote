package com.zhytel.myworknote.domain

import androidx.lifecycle.LiveData
import com.zhytel.myworknote.domain.entity.Note

interface NoteRepository {

    fun addNote(note: Note)

    fun deleteNote(note: Note)

    fun editNote(note: Note)

    fun getNote(noteId: Int): Note

    fun getNoteList():LiveData<List<Note>>
}