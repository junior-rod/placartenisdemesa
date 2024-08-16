package com.example.placartenisdemesa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placartenisdemesa.databinding.ActivityJogosExistentesBinding

class JogosExistentesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogosExistentesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJogosExistentesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvJogosExistentes.layoutManager = LinearLayoutManager(this)

        binding.btnVoltar.setOnClickListener{
            finish()
        }
        }
    }