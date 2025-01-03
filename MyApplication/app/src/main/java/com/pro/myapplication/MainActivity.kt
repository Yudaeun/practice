package com.pro.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.myapplication.databinding.ActivityMainBinding
import com.pro.myapplication.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val list=ArrayList<Person>()

//        for (i in 1..10) {
//            list.add(Person(R.drawable.ic_launcher_background,"김비비","010-1234-1111"))
//        }

        recyclerViewAdapter = RecyclerViewAdapter(list)

        binding.mainRecyclerview.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = recyclerViewAdapter
        }

        binding.btnAdd.setOnClickListener {
            list.add(Person(R.drawable.ic_launcher_background,"김비비","010-1234-1111"))
            recyclerViewAdapter.notifyItemInserted(list.size - 1)
            binding.mainRecyclerview.scrollToPosition(list.size - 1)
        }
    }
}
