package com.example.notesappassignment.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TheProjectDao {

    //for inserting entry in the database
    @Insert
    suspend fun insertNote(theProject: TheProject)

    //fetching the data from the database
    @Query("Select * from yes_thats_me")
    fun showNote():LiveData<List<TheProject>>
}