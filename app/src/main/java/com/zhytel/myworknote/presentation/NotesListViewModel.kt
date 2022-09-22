package com.zhytel.myworknote.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zhytel.myworknote.data.NoteRepositoryImpl
import com.zhytel.myworknote.domain.AddNoteUseCase
import com.zhytel.myworknote.domain.DeleteNoteUseCase
import com.zhytel.myworknote.domain.GetNoteListUseCase
import com.zhytel.myworknote.domain.UpdateTimeUseCase
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
    private val updateTimeUseCase = UpdateTimeUseCase(repository)

    val noteList = getNoteListUseCase()

    fun deleteNote(note: Note){
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun addNote(){
        viewModelScope.launch {
            val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            val current: String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
            val note = Note(
                title = "Новая заметка",
                description = "",
                time = currentTime,
                day = current
            )
            delay(5000)
            addNoteUseCase(note)
        }
    }

    fun updateTimeInNote(note: Note){
        viewModelScope.launch {
            val current: String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
            if(current.toInt() > note.day.toInt()){
                val currentTime: String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
                Log.d("MMM", current)
                val newItem = note.copy(time = currentTime)
                updateTimeUseCase(newItem)
            }
        }
    }

}