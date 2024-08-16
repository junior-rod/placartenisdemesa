package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placartenisdemesa.databinding.ActivityVencedorBinding

class VencedorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVencedorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVencedorBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val nomeVencedor = intent.getStringExtra("nome_vencedor")
        binding.tvVencedor.text = "Vencedor: $nomeVencedor"

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}