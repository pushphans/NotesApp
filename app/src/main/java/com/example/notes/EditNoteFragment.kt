package com.example.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.model.NoteModel
import com.example.notes.viewmodel.NoteViewModel


class EditNoteFragment : Fragment(R.layout.fragment_create_note) {

    private var _binding : FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel : NoteViewModel by activityViewModels()
    private val args : EditNoteFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = args.note
        binding.editTitle.setText(note?.title)
        binding.editBody.setText(note?.content)


        binding.saveNoteFab.setOnClickListener {
            val notes = NoteModel(note!!.id, binding.editTitle.text.toString().trim(), binding.editBody.text.toString().trim() )
            viewModel.updateNote(notes)
            findNavController().popBackStack()
        }

    }


    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = "Edit Notes."
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}