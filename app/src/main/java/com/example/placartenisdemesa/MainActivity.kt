package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.placartenisdemesa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNovoJogo.setOnClickListener {
            startActivity(Intent(this, NovoJogoActivity::class.java))
        }

        binding.btnJogosExistentes.setOnClickListener {
            startActivity(Intent(this, JogosExistentesActivity::class.java))
        }

        binding.btnRegras.setOnClickListener {
            startActivity(Intent(this, RegrasActivity::class.java))
        }
    }
}
