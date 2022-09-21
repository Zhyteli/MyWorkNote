package com.zhytel.myworknote.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.zhytel.myworknote.R
import com.zhytel.myworknote.databinding.ActivityMainBinding
import com.zhytel.myworknote.presentation.adapters.NoteListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}