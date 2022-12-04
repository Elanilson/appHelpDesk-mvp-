package com.apkdoandroid.helpdesk10.view.intefaces

import com.apkdoandroid.helpdesk10.model.entities.HelpDesk

interface HomeView {
    fun mensagem(mensagem : String)
    fun exibirSolicitacoes(solicitacoes : MutableList<HelpDesk>)
    fun status(status : Boolean = false)
}