package co.edu.udem.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes where reminder <> -1")
    fun getAllReminders(): LiveData<List<Note>>

    @Query("SELECT * FROM notes where _id = :noteId")
    fun getNote(noteId: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Delete from notes")
    fun deleteAllNotes()

}