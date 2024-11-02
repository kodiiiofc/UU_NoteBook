package com.kodiiiofc.urbanuniversity.diary.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kodiiiofc.urbanuniversity.diary.data.notes.NoteDatabase
import com.kodiiiofc.urbanuniversity.diary.data.notes.NoteRepositoryImpl
import com.kodiiiofc.urbanuniversity.diary.domain.notes.Note
import com.kodiiiofc.urbanuniversity.diary.domain.notes.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepositoryImpl(dao)
        notes = repository.contacts
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}