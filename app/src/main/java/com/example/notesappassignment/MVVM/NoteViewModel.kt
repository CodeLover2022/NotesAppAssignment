package com.example.notesappassignment.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappassignment.Database.TheProject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repo:NoteRepo):ViewModel() {
    fun getNotes():LiveData<List<TheProject>>
    {
        return repo.getnotes()
    }
    fun insertNote(theProject: TheProject)
    {
   viewModelScope.launch {
       Dispatchers.IO
       repo.insertNote(theProject)
   }
    }
}