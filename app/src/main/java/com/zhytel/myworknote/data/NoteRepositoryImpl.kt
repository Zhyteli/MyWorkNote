package com.zhytel.myworknote.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zhytel.myworknote.domain.NoteRepository
import com.zhytel.myworknote.domain.entity.Note

class NoteRepositoryImpl(
    application: Application
) : NoteRepository {

    private val noteDao = AppDatabase.getInstance(application).noteDao()
    private val mapper = NoteMapper()

    override suspend fun addNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.id)
    }

    override suspend fun editNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun getNote(noteId: Int): Note {
        val dbModel = noteDao.getNote(noteId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getNoteList(): LiveData<List<Note>> = Transformations.map(
        noteDao.getNoteList()
    ){
        mapper.mapListDbModelToEntity(it)
    }

    override suspend fun updateNoteTime(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }
}