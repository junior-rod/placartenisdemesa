package com.example.placartenisdemesa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.placartenisdemesa.databinding.GameItemBinding

class GamesAdapter(
    private val listaDeJogos: List<GameData>,
    private val onItemClick: (GameData) -> Unit
): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameData = listaDeJogos[position]
        holder.bind(gameData)
    }

    override fun getItemCount(): Int = listaDeJogos.size

    inner class GameViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gameData: GameData) {
            binding.tvNomeTorneio.text = gameData.nomeTorneio
            binding.tvJogadores.text = "${gameData.jogadorUm} vs ${gameData.jogadorDois}"
            binding.tvSets.text = "Sets: ${gameData.setsJogadorUm} - ${gameData.setsJogadorDois}"
            binding.tvPontos.text = "Pontos: ${gameData.pontosJogadorUm} - ${gameData.pontosJogadorDois}"

            binding.root.setOnClickListener {
                onItemClick(gameData)
            }
        }
    }
}