package com.zhytel.myworknote.domain

class GetNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(noteId: Int) = repository.getNote(noteId)
}