package com.apkdoandroid.helpdesk10.view.intefaces

interface CadastroView {
   // fun cadastrar(nome : String , senha1 : String , senha2 : String)
    fun mensagem(mensagem : String)
    fun status(status : Boolean = false)
}