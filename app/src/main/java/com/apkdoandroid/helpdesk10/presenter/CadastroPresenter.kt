package com.apkdoandroid.helpdesk10.presenter

import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.Usuario
import com.apkdoandroid.helpdesk10.model.repositorio.UsuarioRepositorio
import com.apkdoandroid.helpdesk10.view.intefaces.CadastroView

class CadastroPresenter {
    private val view : CadastroView
    private val context : Context
    private val repositorio : UsuarioRepositorio

    constructor(view: CadastroView, context: Context) {
        this.view = view
        this.context = context
        repositorio = UsuarioRepositorio.getInstance(context)
    }

    fun cadastrar(nome : String , senha1 : String , senha2 : String){
        if(nome.isNotEmpty() && nome.isNotBlank()){
            if(senha1.isNotEmpty() && senha1.isNotBlank()) {
                if(senha2.isNotEmpty() && senha2.isNotBlank()) {
                    if(senha1.equals(senha2)){
                        if(repositorio.insert(Usuario(nome,senha1))){
                            view.status(true)
                            view.mensagem("Cadastrado com sucesso")
                        }else{
                            view.mensagem("Não conseguir cadastrar")
                        }

                    }else{
                        view.mensagem("A senha não confere")
                    }

                }else{
                    view.mensagem("Preencha o campo confirmar senha")
                }

            }else{
                view.mensagem("Preencha o campo senha")
            }
        }else{
            view.mensagem("Preencha o campo nome!")
        }

    }
}