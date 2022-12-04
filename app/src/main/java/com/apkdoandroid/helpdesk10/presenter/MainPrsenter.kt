package com.apkdoandroid.helpdesk10.presenter

import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.Usuario
import com.apkdoandroid.helpdesk10.model.repositorio.UsuarioRepositorio
import com.apkdoandroid.helpdesk10.view.intefaces.MainView

class MainPrsenter {
    private var view : MainView
    private var context : Context
    private val repositorio : UsuarioRepositorio

    constructor(view: MainView, context: Context) {
        this.view = view
        this.context = context
       repositorio = UsuarioRepositorio.getInstance(context)
    }



    fun login(nome : String, senha : String){
        if(nome.isNotEmpty() && nome.isNotBlank()){
            if(senha.isNotEmpty() && senha.isNotBlank()){
                var usuario : Usuario? = null
               usuario = repositorio.getUsuario(Usuario(nome,senha))

                if(usuario != null){
                    view.status(true)
                    view.mensagem("Login com sucesso")
                }else{
                    view.mensagem("Login ou senha incorreto!")
                }


            }else{
                view.mensagem("Preencha o cmapo senha!")
            }
        }else{
            view.mensagem("Preencha o campo nome!")
        }
    }
}