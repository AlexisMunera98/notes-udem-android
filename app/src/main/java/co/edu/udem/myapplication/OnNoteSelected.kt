package co.edu.udem.myapplication

import co.edu.udem.myapplication.model.Note

interface OnNoteSelected {
    fun onNoteSelected(id: Note)
}