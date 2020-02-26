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

class RemindersAdapter(
    val context: Context, val listener: OnNoteSelected
) : RecyclerView.Adapter<RemindersAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of notes

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.reminder_row, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.reminderTitle.text = current.title

        current.reminder?.let {
            holder.reminderDate.text =
                TimeUtils.formatShortDate(context, it)
        }
        holder.reminderItem.setOnClickListener {
            listener.onNoteSelected(current!!)
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reminderTitle: TextView = itemView.findViewById(R.id.tvReminderTitle)
        val reminderItem: RelativeLayout = itemView.findViewById(R.id.reminder_item)
        val reminderDate: TextView = itemView.findViewById(R.id.tvReminderDate)
    }

}
