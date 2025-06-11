package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentCreateNoteBinding
import com.example.notes.model.NoteModel
import com.example.notes.viewmodel.NoteViewModel


class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNoteFab.setOnClickListener {
            addNote()

        }
    }

    private fun addNote() {
        val title = binding.addTitle.text.toString().trim()
        val body = binding.addBody.text.toString().trim()

        if(title.isEmpty() || body.isEmpty()){
            Toast.makeText(requireContext(),"Something is missing", Toast.LENGTH_SHORT).show()
        }
        else{
            val note = NoteModel(0, title, body)
            viewModel.insertNote(note)
            findNavController().popBackStack()
        }
    }


    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Create New Note"
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}