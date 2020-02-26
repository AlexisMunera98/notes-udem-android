package co.edu.udem.myapplication

import co.edu.udem.myapplication.model.NoteViewModel

interface NoteViewModelContainer {
    fun getViewModel(): NoteViewModel
}
