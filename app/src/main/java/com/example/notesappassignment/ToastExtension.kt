package com.example.notesappassignment

import android.content.Context
import android.widget.Toast

object ToastExtension {
    fun toastMsg(context: Context,s:String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }
}