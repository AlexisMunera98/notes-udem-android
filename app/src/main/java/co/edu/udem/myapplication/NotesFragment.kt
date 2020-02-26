package co.edu.udem.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.myapplication.model.Note
import co.edu.udem.myapplication.model.NoteViewModel


class NotesFragment : Fragment(), OnNoteSelected {


    private var noteViewModel: NoteViewModel? = null

    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_notes, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NotesAdapter(context!!, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        noteViewModel!!.getAllNotes().observe(viewLifecycleOwner, Observer<List<Note>> {
            showNotes(it)
        })

        return rootView

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NoteViewModelContainer) {
            noteViewModel = context.getViewModel()
        }
    }

    fun showNotes(notes: List<Note>) {
        if (notes != null) {
            adapter.setNotes(notes)
        }
    }

    override fun onNoteSelected(note: Note) {

        val intent = Intent(context, EditNoteActivity::class.java)
        intent.putExtra("id", note.mId)
        intent.putExtra("title", note.title)
        intent.putExtra("reminder", note.reminder)

        startActivityForResult(intent, 2)
    }

}
