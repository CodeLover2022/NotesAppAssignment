package com.example.notesappassignment.MVVM

import androidx.lifecycle.LiveData
import com.example.notesappassignment.Database.TheProject
import com.example.notesappassignment.Database.TheProjectDao

class NoteRepo(private val theProjectDao: TheProjectDao) {
     fun getnotes():LiveData<List<TheProject>>
    {
        return theProjectDao.showNote()
    }
    suspend fun insertNote(theProject: TheProject)
    {
        theProjectDao.insertNote(theProject)
    }
}