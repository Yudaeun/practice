package com.example.businesscardapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.businesscardapp.data.Card
import com.example.businesscardapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardViewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
        }

        val adapter = CardAdapter(
            onItemClick = { card->
                val fragment = CardDetailFragment.newInstance(card.imagePath)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }, onItemLongClick = { card ->
                showDeleteDialog(card)
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        cardViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(CardViewModel::class.java)
        cardViewModel.allCards.observe(this){ cards -> adapter.submitList(cards)}

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java))
        }
    }

    private fun showDeleteDialog(card: Card) {
        AlertDialog.Builder(this)
            .setTitle("삭제 확인")
            .setMessage("이 명함을 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                cardViewModel.delete(card)
            }
            .setNegativeButton("취소", null)
            .show()
    }
}