package com.zhytel.myworknote.domain

import com.zhytel.myworknote.domain.entity.Note

class UpdateTimeUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.updateNoteTime(note)
}