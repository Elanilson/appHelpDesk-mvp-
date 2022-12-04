package com.apkdoandroid.helpdesk10.presenter

import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.model.repositorio.HelpDeskRepositorio
import com.apkdoandroid.helpdesk10.view.intefaces.EncerrarView
import java.text.SimpleDateFormat
import java.util.*

class EncerrarPresenter {
    private val view : EncerrarView
    private val context : Context
    private val  repositorio : HelpDeskRepositorio

    constructor(view: EncerrarView, context: Context) {
        this.view = view
        this.context = context

        repositorio = HelpDeskRepositorio.getInstance(context)
    }

    fun getSolicitacao(id : Long){
        view.exibirSolicitacao(repositorio.get(id))
    }

    fun encerraSolicitacao(id : Long, texto : String){
        if (id != null){
            if(!texto.isNullOrEmpty()){
                val date = Calendar.getInstance().time
                var dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
                if(repositorio.update(HelpDesk(id,texto,dateTimeFormat.format(date)))){
                    view.status(true)
                    view.mensagem("Salvo")
                }
            }else{
                view.mensagem("Informe a solução da solicitação")
            }
        }else{
            view.mensagem("Falta o ID da solicitação")
        }

    }
}