package com.apkdoandroid.helpdesk10.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.databinding.ActivitySolicitacaoBinding
import com.apkdoandroid.helpdesk10.presenter.SolicitacaoPresenter
import com.apkdoandroid.helpdesk10.view.intefaces.SolicitacaoView

class SolicitacaoActivity : AppCompatActivity(), SolicitacaoView {
    private lateinit var binding: ActivitySolicitacaoBinding
    private lateinit var presenter : SolicitacaoPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolicitacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = SolicitacaoPresenter(this,binding.root.context)

        binding.imageViewVoltar.setOnClickListener { finish() }
        binding.buttonSalvar.setOnClickListener { salvar() }
    }

    private fun salvar(){
        val patrimonio = binding.editPatrimonio.text.toString()
        val descicao = binding.editDescricao.text.toString()
        presenter.salvarSolicitacao(patrimonio,descicao)
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