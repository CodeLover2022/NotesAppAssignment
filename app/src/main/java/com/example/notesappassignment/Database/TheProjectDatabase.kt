package com.example.notesappassignment.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[TheProject::class], version = 1)
abstract class TheProjectDatabase :RoomDatabase(){
    abstract fun ProjectDao(): TheProjectDao

    companion object {
        @Volatile
        var instance: TheProjectDatabase? = null
        fun invokeDatabase(context: Context): TheProjectDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TheProjectDatabase::class.java,
                        "ProjectDB"
                    ).build()

                }
                return instance!!
            }
            return instance!!
        }
    }
}
