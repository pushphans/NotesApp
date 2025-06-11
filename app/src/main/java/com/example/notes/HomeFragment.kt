package com.example.notes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.adapter.NoteAdapter
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.NoteModel
import com.example.notes.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by activityViewModels()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        viewModel.getAllNote().observe(viewLifecycleOwner) { note ->
            if (note.isEmpty()) {
                binding.rv.visibility = View.GONE
                binding.emptyNoteCard.visibility = View.VISIBLE
            } else {
                binding.rv.visibility = View.VISIBLE
                binding.emptyNoteCard.visibility = View.GONE
                noteAdapter.submitList(note)
            }

        }


        binding.searchbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                        viewModel.searchNote(newText).observe(viewLifecycleOwner) { note ->
                            noteAdapter.submitList(note)
                    }
                }
                else {
                    viewModel.getAllNote().observe(viewLifecycleOwner) {
                        noteAdapter.submitList(it)
                    }
                }
                return true
            }

        })



        binding.addFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = noteAdapter

        noteAdapter.setonOnClicked { note ->
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note)
            findNavController().navigate(action)
        }


        noteAdapter.setonLongClicked { note ->
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("DELETE THIS NOTE?")
            dialog.setMessage("Are you sure you want to delete this note?")
            dialog.setIcon(R.drawable.baseline_delete_24)

            dialog.setPositiveButton("Yes") { DialogInterface, which ->
                viewModel.deleteNote(note)
                Toast.makeText(
                    requireContext(),
                    "Note Deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }


            dialog.setNegativeButton("No") { DialogInterface, which ->
                DialogInterface.dismiss()
                Toast.makeText(requireContext(), "Note not deleted", Toast.LENGTH_SHORT).show()
            }

            dialog.setNeutralButton("Cancel") { DialogInterface, which ->
                DialogInterface.dismiss()
            }

            val alertDailog = dialog.create()
            alertDailog.setCancelable(false)
            alertDailog.show()
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Notes."

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}