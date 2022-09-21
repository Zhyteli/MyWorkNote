package com.zhytel.myworknote.domain

class GetNoteListUseCase(private val repository: NoteRepository) {
    operator fun invoke() = repository.getNoteList()
}