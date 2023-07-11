package com.example.notesappassignment

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappassignment.Database.TheProject
import com.example.notesappassignment.Database.TheProjectDatabase
import com.example.notesappassignment.MVVM.NoteRepo
import com.example.notesappassignment.MVVM.NoteViewModel
import com.example.notesappassignment.MVVM.NoteViewModelFactory
import com.example.notesappassignment.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
  private var _binding:FragmentHomeBinding?=null
  private lateinit var arr:List<TheProject>
   private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao=TheProjectDatabase.invokeDatabase(requireContext()).ProjectDao()

        val repository=NoteRepo(dao)
        //initializing the arraylist
        arr= ArrayList()
        val viewModel=ViewModelProvider(this,NoteViewModelFactory(repository)).get(NoteViewModel::class.java)
        recyclerViewInitialization(viewModel)
  binding.btnSubmit.setOnClickListener {
      showEditTextDialog(viewModel)
  }
    }

    private fun recyclerViewInitialization(viewModel: NoteViewModel) {
        viewModel.getNotes().observe(viewLifecycleOwner,{
            binding.rvShowNote.layoutManager=StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
            arr=it
            if(it.size!=0){
            binding.textView.visibility=View.INVISIBLE
            }

            binding.rvShowNote.visibility=View.VISIBLE
            binding.rvShowNote.adapter=NoteAdapter(requireContext(),arr)
        }
        )

       }



    private fun showEditTextDialog(viewModel: NoteViewModel) {
        val builder=AlertDialog.Builder(requireContext())
        val dialogBox=layoutInflater.inflate(R.layout.dialog_layout,null)
        val editbox=dialogBox.findViewById<EditText>(R.id.editNote)
        with(builder){
            setTitle("Enter text")
            setPositiveButton("OK") {dialog,which->
                if(editbox.text.isEmpty())
                {
                    ToastExtension.toastMsg(context,"please enter some text")
                }
                else
                {
                    val note=editbox.text.toString()
                    viewModel.insertNote(TheProject(0,note))
                }

            }
            setNegativeButton("Cancel"){
                dialog,which -> ToastExtension.toastMsg(context,"Cancelled")
            }
            setView(dialogBox)
            show()
        }

    }
}
