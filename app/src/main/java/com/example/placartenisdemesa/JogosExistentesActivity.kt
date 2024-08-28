package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placartenisdemesa.databinding.ActivityJogosExistentesBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JogosExistentesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogosExistentesBinding
    private lateinit var adapter: GamesAdapter
    private val gamesList = mutableListOf<GameData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJogosExistentesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            finish()
        }

        binding.rvJogosExistentes.layoutManager = LinearLayoutManager(this)
        adapter = GamesAdapter(gamesList) { gameData ->
            continuarJogo(gameData)
        }
        binding.rvJogosExistentes.adapter = adapter

        carregarJogosSalvos()
    }

    private fun carregarJogosSalvos() {
        val sharedPreferences = getSharedPreferences("placar_tenis_de_mesa", MODE_PRIVATE)
        val gson = Gson()

        val allEntries = sharedPreferences.all
        for ((key, value) in allEntries) {
            val json = value as String
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val gameDataMap: Map<String, Any> = gson.fromJson(json, type)
            val gameData = GameData(
                key,
                gameDataMap["nomeTorneio"] as? String ?: "Nome do torneio desconhecido.",
                gameDataMap["jogador_um"] as String,
                gameDataMap["jogador_dois"] as String,
                (gameDataMap["sets_jogador_um"] as Double).toInt(),
                (gameDataMap["sets_jogador_dois"] as Double).toInt(),
                (gameDataMap["pontos_jogador_um"] as Double).toInt(),
                (gameDataMap["pontos_jogador_dois"] as Double).toInt()
            )
            gamesList.add(gameData)
        }
        adapter.notifyDataSetChanged()
    }

    private fun continuarJogo(gameData: GameData) {
        val intent = Intent(this, JogoActivity::class.java)
        intent.putExtra("game_data", gameData)
        startActivity(intent)
    }
}

//data class GameData(
//    val id: String,
//    val jogadorUm: String,
//    val jogadorDois: String,
//    val setsJogadorUm: Int,
//    val setsJogadorDois: Int,
//    val pontosJogadorUm: Int,
//    val pontosJogadorDois: Int
//)