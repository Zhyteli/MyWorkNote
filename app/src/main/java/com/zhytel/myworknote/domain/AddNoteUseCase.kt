package com.zhytel.myworknote.domain

import com.zhytel.myworknote.domain.entity.Note

class AddNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(note: Note) = repository.addNote(note)
}