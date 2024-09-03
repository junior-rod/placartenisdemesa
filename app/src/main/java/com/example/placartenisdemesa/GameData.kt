package com.example.placartenisdemesa

import android.os.Parcel
import android.os.Parcelable

class GameData(
    val id: String,
    val nomeTorneio: String,
    val jogadorUm: String,
    val jogadorDois: String,
    val setsJogadorUm: Int,
    val setsJogadorDois: Int,
    val pontosJogadorUm: Int,
    val pontosJogadorDois: Int,
    val quantidadeSets: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nomeTorneio)
        parcel.writeString(jogadorUm)
        parcel.writeString(jogadorDois)
        parcel.writeInt(setsJogadorUm)
        parcel.writeInt(setsJogadorDois)
        parcel.writeInt(pontosJogadorUm)
        parcel.writeInt(pontosJogadorDois)
        parcel.writeInt(quantidadeSets)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameData> {
        override fun createFromParcel(parcel: Parcel): GameData {
            return GameData(parcel)
        }

        override fun newArray(size: Int): Array<GameData?> {
            return arrayOfNulls(size)
        }
    }
}