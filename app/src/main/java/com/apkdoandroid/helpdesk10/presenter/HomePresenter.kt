package com.apkdoandroid.helpdesk10.presenter

import android.content.Context
import com.apkdoandroid.helpdesk10.model.repositorio.HelpDeskRepositorio
import com.apkdoandroid.helpdesk10.view.intefaces.HomeView

class HomePresenter {
    private val view : HomeView
    private val context : Context
    private  val repositorio : HelpDeskRepositorio

    constructor(view: HomeView, context: Context) {
        this.view = view
        this.context = context
        repositorio = HelpDeskRepositorio.getInstance(context)
    }

    fun carregarTodasSolicitacoes(){
        val solicitacoes = repositorio.getAll()
       if(!solicitacoes.isNullOrEmpty()){
           view.status(true)
           view.exibirSolicitacoes(solicitacoes)
       }else{
           view.mensagem("Sem daddos "+solicitacoes.size)
       }

    }
    fun carregar_Solicitacoes_Em_Andamento(){
        val solicitacoes = repositorio.getSolicitacoesAbertas()
        if(!solicitacoes.isNullOrEmpty()){
            view.status(true)
            view.exibirSolicitacoes(solicitacoes)
        }
    }
    fun carregar_Solicitacoes_finalizadas(){
        val solicitacoes = repositorio.getSolicitacoesEncerradas()
        if(!solicitacoes.isNullOrEmpty()){
            view.status(true)
            view.exibirSolicitacoes(solicitacoes)
        }
    }
}