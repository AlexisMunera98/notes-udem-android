package co.edu.udem.myapplication.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteDao: NotesDao

    private var allNotes: LiveData<List<Note>> = getAllNotes()
    private var allReminders: LiveData<List<Note>> = getAllReminders()

    init {
        val database: MyDatabase = MyDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.notesDao()
        allNotes = noteDao.getAllNotes()
        allReminders = noteDao.getAllReminders()
    }


    fun fetchNote(noteId: Int): Note {
        return noteDao.getNote(noteId)
    }

    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(noteDao).execute(note)
    }


    fun delete(note: Note) {
        val deleteNoteAsyncTask = DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    fun getAllReminders(): LiveData<List<Note>> {
        return allReminders
    }

    companion object {



        private class InsertNoteAsyncTask(noteDao: NotesDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Note?) {
                noteDao.insertNote(p0[0]!!)
            }
        }

        private class UpdateNoteAsyncTask(noteDao: NotesDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Note?) {
                noteDao.update(p0[0]!!)
            }
        }

        private class DeleteNoteAsyncTask(noteDao: NotesDao) : AsyncTask<Note, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Note?) {
                noteDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllNotesAsyncTask(noteDao: NotesDao) : AsyncTask<Unit, Unit, Unit>() {
            val noteDao = noteDao

            override fun doInBackground(vararg p0: Unit?) {
                noteDao.deleteAllNotes()
            }
        }
    }
}