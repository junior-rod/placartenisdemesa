package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placartenisdemesa.databinding.ActivitySorteioBinding
import kotlin.random.Random

class SorteioActivity : AppCompatActivity() {
    lateinit var binding: ActivitySorteioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySorteioBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val nomeTorneio = intent.getStringExtra("nome_torneio")
        val jogadorUm = intent.getStringExtra("jogador_um")
        val jogadorDois = intent.getStringExtra("jogador_dois")
        val quantidadeSets = intent.getIntExtra("quantidade_sets", 1)

        Handler(Looper.getMainLooper()).postDelayed({
            val jogadorInicial = if (Random.nextBoolean()) jogadorUm else jogadorDois
            binding.tvResultadoSorteio.text = "O jogador inicial é: $jogadorInicial"
            Handler(Looper.getMainLooper()).postDelayed({
                val jogoIntent = Intent(this, JogoActivity::class.java)
                jogoIntent.putExtra("jogador_inicial", jogadorInicial)
                jogoIntent.putExtra("jogador_um", jogadorUm)
                jogoIntent.putExtra("jogador_dois", jogadorDois)
                jogoIntent.putExtra("nome_torneio", nomeTorneio)
                jogoIntent.putExtra("quantidade_sets", quantidadeSets)
                startActivity(jogoIntent)
                finish()
            }, 2000)
        }, 2000)
    }
}