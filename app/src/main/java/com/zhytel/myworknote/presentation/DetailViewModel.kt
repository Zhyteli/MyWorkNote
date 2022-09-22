package com.zhytel.myworknote.presentation

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.zhytel.myworknote.data.NoteRepositoryImpl
import com.zhytel.myworknote.domain.*
import com.zhytel.myworknote.domain.entity.Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class DetailViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val getNoteUseCase = GetNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    private val _noteItem = MutableLiveData<Note>()
    val noteItem: LiveData<Note>
        get() = _noteItem

    fun getShopItem(noteId: Int) {
        viewModelScope.launch {
            val item = getNoteUseCase(noteId)
            _noteItem.value = item
        }
    }

    fun editShopItem(inputTitle: String?, inputDescription: String?) {
        val title = parseInput(inputTitle)
        val description = parseInput(inputDescription)
        val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        _noteItem.value?.let {
            viewModelScope.launch {
                val item = it.copy(title = title, description = description, time = currentTime)
                delay(5000)
                editNoteUseCase(item)
                Toast.makeText(getApplication(), "Сохранено", Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun parseInput(inputName: String?): String {
        return inputName?.trim() ?: ""
    }
}