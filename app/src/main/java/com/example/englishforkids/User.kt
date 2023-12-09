package com.example.englishforkids

class User(var id: Int, var name: String, var score: Int,var idLevel: Int) {
    override fun toString(): String {
        return "User(id=$id, name=$name, score=$score, idLevel=$idLevel)"
    }
}