package com.zhytel.myworknote.domain

import androidx.lifecycle.LiveData
import com.zhytel.myworknote.domain.entity.Note

interface NoteRepository {

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun editNote(note: Note)

    suspend fun getNote(noteId: Int): Note

    fun getNoteList():LiveData<List<Note>>
}