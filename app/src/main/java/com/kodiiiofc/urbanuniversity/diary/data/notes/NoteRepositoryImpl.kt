package com.kodiiiofc.urbanuniversity.diary.data.notes

import androidx.lifecycle.LiveData
import com.kodiiiofc.urbanuniversity.diary.data.notes.NoteDao
import com.kodiiiofc.urbanuniversity.diary.domain.notes.Note
import com.kodiiiofc.urbanuniversity.diary.domain.notes.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override val contacts: LiveData<List<Note>> = noteDao.getAllNotes()

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

}