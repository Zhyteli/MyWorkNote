package com.zhytel.myworknote.domain

class GetNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(noteId: Int) = repository.getNote(noteId)
}