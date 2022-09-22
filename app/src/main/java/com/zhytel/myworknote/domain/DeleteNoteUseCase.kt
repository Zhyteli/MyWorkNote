package com.zhytel.myworknote.domain

import com.zhytel.myworknote.domain.entity.Note

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
}