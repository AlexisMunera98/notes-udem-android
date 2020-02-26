package co.edu.udem.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.udem.myapplication.model.Note

class NotesAdapter(
    context: Context, val listener: OnNoteSelected
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of notes

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_row, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.noteItemView.text = current.title
        if (current.reminder == -1L) {
            holder.reminderIcon.visibility = View.INVISIBLE
        }
        holder.noteItem.setOnClickListener {
            listener.onNoteSelected(current!!)
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.textView3)
        val reminderIcon: ImageView = itemView.findViewById(R.id.reminderIcon)
        val noteItem: RelativeLayout = itemView.findViewById(R.id.note_item)
    }

}
