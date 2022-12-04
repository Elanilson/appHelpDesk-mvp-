package com.apkdoandroid.helpdesk10.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.view.intefaces.OnHelpDeskListener
import com.apkdoandroid.helpdesk10.view.viewholder.SolicitacoesViewHolder

class SolicitacoesAdapter : RecyclerView.Adapter<SolicitacoesViewHolder>() {
    private var solicitacoes : MutableList<HelpDesk> = arrayListOf()
    private lateinit var onHelpDeskListener: OnHelpDeskListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitacoesViewHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_chamado,parent,false)
        return SolicitacoesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SolicitacoesViewHolder, position: Int) {
        holder.bind(solicitacoes[position],onHelpDeskListener)
    }

    override fun getItemCount(): Int {
        return solicitacoes.count()
    }

    fun attackSolicitacoes(solicitacoes : MutableList<HelpDesk>){
        this.solicitacoes = solicitacoes
        notifyDataSetChanged()
    }
    fun attackListener(onHelpDeskListener: OnHelpDeskListener){
        this.onHelpDeskListener = onHelpDeskListener
    }
    fun limpar(){
       // this.solicitacoes.removeAll(solicitacoes)
        this.solicitacoes.clear()
        notifyDataSetChanged()
    }
}