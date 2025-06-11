package com.example.notes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.db.NoteDatabase
import com.example.notes.repository.NoteRepository
import com.example.notes.viewmodel.NoteViewModel
import com.example.notes.viewmodel.VMFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Notes"


        val db = NoteDatabase.getDatabase(this)
        val repo = NoteRepository(db)
        viewModel = ViewModelProvider(this, VMFactory(repo))[NoteViewModel::class.java]



    }
}