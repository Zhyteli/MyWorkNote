package com.zhytel.myworknote.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zhytel.myworknote.data.NoteRepositoryImpl
import com.zhytel.myworknote.domain.AddNoteUseCase
import com.zhytel.myworknote.domain.DeleteNoteUseCase
import com.zhytel.myworknote.domain.EditNoteUseCase
import com.zhytel.myworknote.domain.GetNoteListUseCase
import com.zhytel.myworknote.domain.entity.Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NotesListViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)

    val noteList = getNoteListUseCase()

    fun deleteNote(note: Note){
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun addNote(){
        viewModelScope.launch {
            val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            val note = Note(
                title = "Новая заметка",
                description = "",
                time = currentTime
            )
            delay(5000)
            addNoteUseCase(note)
        }
    }

}