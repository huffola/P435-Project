package com.example.volleyrest.api

class Item(
    var name: String,
    var members: String,
    var examine: String,
    var tradeable: String,
) {

    override fun toString(): String {
        return "${name}: $members ($tradeable) at $examine"
    }
}