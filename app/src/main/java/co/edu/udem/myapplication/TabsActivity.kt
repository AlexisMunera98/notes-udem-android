package co.edu.udem.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import co.edu.udem.myapplication.model.Note
import co.edu.udem.myapplication.model.NoteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_second.*

class TabsActivity : AppCompatActivity(), NoteViewModelContainer {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bottom_nav.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED


        fabBtn.setOnClickListener { view -> createNote() }

        val navController = findNavController(R.id.myNavHostFragment)
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

    }

    private fun createNote() {
        val intent = Intent(this, EditNoteActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val noteId = data?.getIntExtra("id", -1)
        val noteTitle = data?.getStringExtra("title")
        val noteReminder = data?.getLongExtra("reminder", -1L)

        if (noteId == -1) {
            val note = Note()
            note.title = noteTitle
            note.reminder = noteReminder
            noteViewModel.insert(note)
        } else {
            noteId?.let {
                var note = noteViewModel.fetchNote(noteId)
                note.title = noteTitle
                note.reminder = noteReminder
                noteViewModel.update(note)
            }
        }

    }

    override fun getViewModel(): NoteViewModel {
        return noteViewModel
    }

}
