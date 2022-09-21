package com.zhytel.myworknote.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Query("DELETE FROM note_items WHERE id=:noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM note_items WHERE id=:shopItemId LIMIT 1")
    suspend fun getNote(shopItemId: Int): NoteDbModel

    @Query("SELECT * FROM note_items")
    fun getNoteList(): LiveData<List<NoteDbModel>>
}