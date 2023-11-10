package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {


    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NotesDatabaseHelper(this)

        noteID = intent.getIntExtra("note_id", -1)
        if (noteID == -1){
            finish()
            return
        }
        val note = db.getNoteByID(noteID)
        binding.updtitleEDT.setText(note.title)
        binding.updcontentEDT.setText(note.content)

        binding.UpdateButton.setOnClickListener{
            val newTitle = binding.updtitleEDT.text.toString()
            val newContent = binding.updcontentEDT.text.toString()
            val updateNote = Note(noteID, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()

        }
    }
}