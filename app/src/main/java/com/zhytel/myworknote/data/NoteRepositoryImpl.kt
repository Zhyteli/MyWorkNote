package com.zhytel.myworknote.data

import androidx.lifecycle.LiveData
import com.zhytel.myworknote.domain.NoteRepository
import com.zhytel.myworknote.domain.entity.Note

class NoteRepositoryImpl: NoteRepository {
    override fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun editNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun getNote(noteId: Int): Note {
        TODO("Not yet implemented")
    }

    override fun getNoteList(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }
}