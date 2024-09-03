package com.example.placartenisdemesa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            val quantidadeSets = when (binding.rgQuantidadeSets.checkedRadioButtonId) {
                R.id.radioButton1 -> 1
                R.id.radioButton3 -> 3
                R.id.radioButton5 -> 5
                R.id.radioButton7 -> 7
                else -> 1
            }
            intent.putExtra("quantidade_sets", quantidadeSets)
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener{
            finish()
        }



    }
}