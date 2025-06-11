package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.model.NoteModel
import kotlin.concurrent.Volatile


@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getDao() : NoteDao

    companion object{
        @Volatile
        private var instance : NoteDatabase? = null

        fun getDatabase(context: Context) : NoteDatabase{
            if(instance == null){
                synchronized(this){
                    if(instance == null){
                        instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "database").build()
                    }
                }
            }
            return instance!!
        }
    }
}