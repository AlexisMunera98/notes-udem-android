package co.edu.udem.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import co.edu.udem.myapplication.model.NotesDao
import kotlinx.android.synthetic.main.activity_edit_note.*
import java.util.*

class EditNoteActivity : AppCompatActivity() {

    private var noteTitle: String? = null
    private var noteId: Int = -1
    private var reminderDate: Long = -1
    private lateinit var notesDao: NotesDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        noteId = intent.getIntExtra("id", -1)
        noteTitle = intent.getStringExtra("title")
        reminderDate = intent.getLongExtra("reminder", -1L)

        if (noteTitle != null) {
            editText.setText(noteTitle)
        }

        val today = Calendar.getInstance()
        if (reminderDate != -1L) {
            today.timeInMillis = reminderDate
            tvReminder.text = TimeUtils.formatShortDate(this, reminderDate)
        }

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        btnReminder.setOnClickListener {
            val dpd =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
                    val c = Calendar.getInstance()
                    c.set(i, i2, i3)
                    reminderDate = c.timeInMillis
                    tvReminder.text = TimeUtils.formatShortDate(this, reminderDate)
                }, year, month, day).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        } else if (id == R.id.action_save) {


            noteTitle = editText.text.toString()
            var intent = Intent()
            intent.putExtra("id", noteId)
            intent.putExtra("title", noteTitle)
            intent.putExtra("reminder", reminderDate)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
