package com.apkdoandroid.helpdesk10.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.view.intefaces.OnHelpDeskListener

class SolicitacoesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var linearLayout : LinearLayout
    private lateinit var textPatrimonio : TextView
    private lateinit var textDataCreate : TextView
    private lateinit var imagemSelo : ImageView
    private lateinit var card : CardView

    fun bind(helpDesk: HelpDesk,onHelpDeskListener: OnHelpDeskListener){
        linearLayout = itemView.findViewById(R.id.linearLayoutFundo)
        textPatrimonio = itemView.findViewById(R.id.textViewPatrimonio)
        textDataCreate = itemView.findViewById(R.id.textViewDataCreate)
        imagemSelo = itemView.findViewById(R.id.imageViewSelo)
        card = itemView.findViewById(R.id.cardSolicitacao)

        if(helpDesk.data_encerramento.isNotEmpty()){
            linearLayout.setBackgroundResource(R.color.verde100)
            imagemSelo.setImageResource(R.drawable.ic_baseline_task_alt_24)
        }else{
            linearLayout.setBackgroundResource(R.color.vermleho500)
            imagemSelo.setImageResource(R.drawable.ic_atencao_24)
        }

        textPatrimonio.setText("Patrimônio: "+helpDesk.patrimonio)
        textDataCreate.setText(helpDesk.data_create)
        card.setOnClickListener { onHelpDeskListener.onClick(helpDesk) }

    }
}