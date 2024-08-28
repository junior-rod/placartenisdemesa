package com.example.placartenisdemesa

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.placartenisdemesa.databinding.ActivityJogoBinding
import com.google.gson.Gson

class JogoActivity : AppCompatActivity() {
    lateinit var binding: ActivityJogoBinding
    private var setsJogadorUm = 0
    private var setsJogadorDois = 0
    private var pontosJogadorUm = 0
    private var pontosJogadorDois = 0
    private val historicoPontos = mutableListOf<Pair<String, Int>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJogoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val gameData: GameData? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("game_data", GameData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("game_data")
        }
        if (gameData != null) {
            binding.tvNomeTorneio.text = gameData.nomeTorneio
            binding.tvJogadorUm.text = gameData.jogadorUm
            binding.tvJogadorDois.text = gameData.jogadorDois
            setsJogadorUm = gameData.setsJogadorUm
            setsJogadorDois = gameData.setsJogadorDois
            pontosJogadorUm = gameData.pontosJogadorUm
            pontosJogadorDois = gameData.pontosJogadorDois
            atualizar()
        } else {

            val jogadorUm = intent.getStringExtra("jogador_um") ?: "Jogador 1"
            val jogadorDois = intent.getStringExtra("jogador_dois") ?: "Jogador 2"
            binding.tvJogadorUm.text = jogadorUm
            binding.tvJogadorDois.text = jogadorDois
        }
        binding.tvJogadorUm.setOnClickListener { incrementarPonto("jogador_um") }
        binding.tvPontosJogadorUm.setOnClickListener { incrementarPonto("jogador_dois") }
        binding.tvJogadorDois.setOnClickListener { incrementarPonto("jogador_um") }
        binding.tvPontosJogadorDois.setOnClickListener { incrementarPonto("jogador_dois") }

        val nomeTorneio = intent.getStringExtra("nome_torneio") ?: "Torneio"
        val jogadorUm = intent.getStringExtra("jogador_um") ?: "Jogador 1"
        val jogadorDois = intent.getStringExtra("jogador_dois") ?: "Jogador 2"

        binding.tvNomeTorneio.text = nomeTorneio
        binding.tvJogadorUm.text = jogadorUm
        binding.tvJogadorDois.text = jogadorDois

        binding.tvJogadorUm.setOnClickListener { incrementarPonto("jogador_um") }
        binding.tvPontosJogadorUm.setOnClickListener { incrementarPonto("jogador_um") }
        binding.tvJogadorDois.setOnClickListener { incrementarPonto("jogador_dois") }
        binding.tvPontosJogadorDois.setOnClickListener { incrementarPonto("jogador_dois") }

        binding.btnDesfazer.setOnClickListener { desfazerUltimoPonto() }
    }

    private fun incrementarPonto(jogador: String) {
        when (jogador) {
            "jogador_um" -> {
                pontosJogadorUm++
                historicoPontos.add(Pair("jogador_um", pontosJogadorUm))
                vencedorSet(jogador)
                atualizar()
            }

            "jogador_dois" -> {
                pontosJogadorDois++
                historicoPontos.add(Pair("jogador_dois", pontosJogadorDois))
                vencedorSet(jogador)
                atualizar()
            }
        }
    }

    private fun vencedorSet(jogador: String) {
        if ((jogador == "jogador_um" && pontosJogadorUm >= 11 && pontosJogadorUm - pontosJogadorDois >= 2) ||
            (jogador == "jogador_dois" && pontosJogadorDois >= 11 && pontosJogadorDois - pontosJogadorUm >= 2)
        ) {
            if (jogador == "jogador_um") {
                setsJogadorUm++
            } else {
                setsJogadorDois++
            }
            pontosJogadorUm = 0
            pontosJogadorDois = 0
            if (setsJogadorUm == 4 || setsJogadorDois == 4) {
                val vencedor = if (setsJogadorUm == 4) "jogador_um" else "jogador_dois"
                mostrarVencedor(vencedor)
            }
        }
    }

    private fun mostrarVencedor(vencedor: String) {
        val nomeVencedor = if (vencedor == "jogador_um") {
            binding.tvJogadorUm.text.toString()
        } else {
            binding.tvJogadorDois.text.toString()
        }

        val intent = Intent(this, VencedorActivity::class.java)
        intent.putExtra("nome_vencedor", nomeVencedor)
        startActivity(intent)
        finish()
    }

    private fun desfazerUltimoPonto() {
        if (historicoPontos.isNotEmpty()) {
            val ultimaAcao = historicoPontos.removeAt(historicoPontos.size - 1)
            when (ultimaAcao.first) {
                "jogador_um" -> pontosJogadorUm = ultimaAcao.second - 1
                "jogador_dois" -> pontosJogadorDois = ultimaAcao.second - 1
            }
            atualizar()
        }
    }

    private fun atualizar() {
        binding.tvPontosJogadorUm.text = "Pontos: $pontosJogadorUm"
        binding.tvSetsJogadorUm.text = "Sets: $setsJogadorUm"
        binding.tvPontosJogadorDois.text = "Pontos: $pontosJogadorDois"
        binding.tvSetsJogadorDois.text = "Sets: $setsJogadorDois"
    }
    private fun salvarJogo() {
        val sharedPreferences = getSharedPreferences("placar_tenis_de_mesa", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gameData = mapOf(
            "nomeTorneio" to binding.tvNomeTorneio.text.toString(),
            "jogador_um" to binding.tvJogadorUm.text.toString(),
            "jogador_dois" to binding.tvJogadorDois.text.toString(),
            "sets_jogador_um" to setsJogadorUm,
            "sets_jogador_dois" to setsJogadorDois,
            "pontos_jogador_um" to pontosJogadorUm,
            "pontos_jogador_dois" to pontosJogadorDois
        )

        // Serializar o mapa de dados para uma string JSON
        val gson = Gson()
        val json = gson.toJson(gameData)

        // Salvar a partida com uma chave única (timestamp)
        val timestamp = System.currentTimeMillis().toString()
        editor.putString(timestamp, json)
        editor.apply()
    }

    override fun onPause() {
        super.onPause()
        salvarJogo()  // Salvar o estado do jogo ao sair da activity
    }

}