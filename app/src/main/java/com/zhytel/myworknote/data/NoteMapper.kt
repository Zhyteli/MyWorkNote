package com.zhytel.myworknote.data

import com.zhytel.myworknote.domain.entity.Note

class NoteMapper {
    fun mapEntityToDbModel(note: Note) = NoteDbModel(
        id = note.id,
        title = note.title,
        description = note.description,
        time = note.time
    )

    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        title = noteDbModel.title,
        description = noteDbModel.description,
        time = noteDbModel.time
    )

    fun mapListDbModelToEntity(list: List<NoteDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}