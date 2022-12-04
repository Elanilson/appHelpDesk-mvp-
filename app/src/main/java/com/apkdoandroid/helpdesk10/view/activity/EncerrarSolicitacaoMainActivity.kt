package com.apkdoandroid.helpdesk10.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.databinding.ActivityEncerrarSolicitacaoMainBinding
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.presenter.EncerrarPresenter
import com.apkdoandroid.helpdesk10.view.intefaces.EncerrarView

class EncerrarSolicitacaoMainActivity : AppCompatActivity(),EncerrarView {
    private lateinit var binding: ActivityEncerrarSolicitacaoMainBinding
    private lateinit var presenter: EncerrarPresenter
    private var id : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEncerrarSolicitacaoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = EncerrarPresenter(this,binding.root.context)

        val bundle = intent.extras
        if(bundle != null){
             id = bundle.getLong("id")
            presenter.getSolicitacao(id)
        }

        binding.buttonSalvar.setOnClickListener { encerrar() }
        binding.imageViewVoltar.setOnClickListener { finish() }
    }

    private fun encerrar (){
        var solucao = binding.editSolucao.text.toString()

        presenter.encerraSolicitacao(id,solucao)
    }

    override fun exibirSolicitacao(helpDesk: HelpDesk?) {
        binding.textViewPatrimonio.setText("Patrimônio: ${helpDesk?.patrimonio}")
        binding.textViewDescricao.setText(helpDesk?.descricao)
        binding.textViewDAta.setText("Registrado em ${helpDesk?.data_create}")

        if(!helpDesk?.data_encerramento.isNullOrEmpty()){
            binding.editSolucao.setText(helpDesk?.solucao)
            binding.editSolucao.setTextColor(resources.getColor(R.color.white))
            binding.editSolucao.setBackgroundColor(resources.getColor(R.color.azul800))
            binding.editSolucao.isEnabled = false
            binding.buttonSalvar.visibility = View.GONE
        }
    }

    override fun mensagem(mensagem: String) {
        Toast.makeText(this,mensagem, Toast.LENGTH_SHORT).show()
    }

    override fun status(status: Boolean) {

        if(status){
            finish()
        }

    }
}