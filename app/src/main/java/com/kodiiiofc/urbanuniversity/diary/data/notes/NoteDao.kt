package com.kodiiiofc.urbanuniversity.diary.data.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kodiiiofc.urbanuniversity.diary.domain.notes.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    fun deleteAll()
}
