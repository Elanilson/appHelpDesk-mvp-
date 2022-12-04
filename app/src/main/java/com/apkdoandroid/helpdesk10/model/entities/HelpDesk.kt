package com.apkdoandroid.helpdesk10.model.entities

class HelpDesk {
    var id : Long = 0
    var patrimonio : String = ""
    var descricao : String = ""
    var solucao : String = ""
    var data_create : String = ""
    var data_encerramento : String = ""

    constructor()
    constructor(
        id: Long,
        patrimonio: String,
        descricao: String,
        solucao: String,
        data_create: String,
        data_encerramento: String
    ) {
        this.id = id
        this.patrimonio = patrimonio
        this.descricao = descricao
        this.solucao = solucao
        this.data_create = data_create
        this.data_encerramento = data_encerramento
    }

    constructor(patrimonio: String, descricao: String, data_create: String) {
        this.patrimonio = patrimonio
        this.descricao = descricao
        this.data_create = data_create
    }

    constructor(id: Long, solucao: String, data_encerramento: String) {
        this.id = id
        this.solucao = solucao
        this.data_encerramento = data_encerramento
    }


}