package com.apkdoandroid.helpdesk10.model.entities

class Usuario {
    var id : Long = 0
    var nome : String = ""
    var senha : String = ""

    constructor()
    constructor(id: Long, nome: String, senha: String) {
        this.id = id
        this.nome = nome
        this.senha = senha
    }

    constructor(nome: String, senha: String) {
        this.nome = nome
        this.senha = senha
    }


}