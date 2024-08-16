package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placartenisdemesa.databinding.ActivityNovoJogoBinding

class NovoJogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoJogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNovoJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnComecarJogo.setOnClickListener{
            val intent = Intent(this, SorteioActivity::class.java)
            intent.putExtra("nome_torneio", binding.etNomeTorneio.text.toString())
            intent.putExtra("jogador_um", binding.etJogadorUm.text.toString())
            intent.putExtra("jogador_dois", binding.etJogadorDois.text.toString())
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener{
            finish()
        }

    }
}