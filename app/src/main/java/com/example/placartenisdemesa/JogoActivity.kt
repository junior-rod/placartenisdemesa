package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.placartenisdemesa.databinding.ActivityJogoBinding

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
                checkVencedorSet(jogador)
                Atualizar()
            }

            "jogador_dois" -> {
                pontosJogadorDois++
                historicoPontos.add(Pair("jogador_dois", pontosJogadorDois))
                checkVencedorSet(jogador)
                Atualizar()
            }
        }
    }

    private fun checkVencedorSet(jogador: String) {
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
            Atualizar()
        }
    }

    private fun Atualizar() {
        binding.tvPontosJogadorUm.text = "Pontos: $pontosJogadorUm"
        binding.tvSetsJogadorUm.text = "Sets: $setsJogadorUm"
        binding.tvPontosJogadorDois.text = "Pontos: $pontosJogadorDois"
        binding.tvSetsJogadorDois.text = "Sets: $setsJogadorDois"
    }


}