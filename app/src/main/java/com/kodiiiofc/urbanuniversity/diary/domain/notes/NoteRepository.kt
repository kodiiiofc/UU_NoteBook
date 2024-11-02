package com.kodiiiofc.urbanuniversity.diary.domain.notes

import androidx.lifecycle.LiveData

interface NoteRepository {

    val contacts: LiveData<List<Note>>

    suspend fun insert(note: Note)
    suspend fun delete(note: Note)
}