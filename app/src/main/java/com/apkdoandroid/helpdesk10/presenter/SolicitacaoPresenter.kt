package com.apkdoandroid.helpdesk10.presenter

import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.model.repositorio.HelpDeskRepositorio
import com.apkdoandroid.helpdesk10.view.intefaces.SolicitacaoView
import java.text.SimpleDateFormat
import java.util.*

class SolicitacaoPresenter {
    private val view : SolicitacaoView
    private val content : Context
    private val repositorio : HelpDeskRepositorio

    constructor(view: SolicitacaoView, content: Context) {
        this.view = view
        this.content = content
        repositorio = HelpDeskRepositorio.getInstance(content)
    }
    fun salvarSolicitacao(patrimonio : String , descricao : String){
        if(patrimonio.isNotEmpty() && patrimonio.isNotBlank()){
            if(descricao.isNotEmpty() && descricao.isNotBlank()){
                val date = Calendar.getInstance().time
                var dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())


               if(repositorio.insert(HelpDesk(patrimonio,descricao,dateTimeFormat.format(date)))){
                   view.status(true)
                   view.mensagem("Salvo")
               }else{
                   view.mensagem("Não foi possível salvar")
               }

            }else{
                view.mensagem("Preencha o campo Descricao")
            }
        }else{
            view.mensagem("Preencha o campo patrimonio")
        }

    }
}