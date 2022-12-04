package com.apkdoandroid.helpdesk10.view.intefaces

import com.apkdoandroid.helpdesk10.model.entities.HelpDesk

interface EncerrarView {
    fun exibirSolicitacao(helpDesk: HelpDesk?)
    fun mensagem(mensagem : String)
    fun status(status : Boolean = false)
}