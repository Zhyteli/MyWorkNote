package com.zhytel.myworknote.domain

import com.zhytel.myworknote.domain.entity.Note

class EditNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.editNote(note)
}