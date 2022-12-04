package com.apkdoandroid.helpdesk10.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apkdoandroid.helpdesk10.MainActivity
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.databinding.ActivityCadastroBinding
import com.apkdoandroid.helpdesk10.presenter.CadastroPresenter
import com.apkdoandroid.helpdesk10.view.intefaces.CadastroView

class CadastroActivity : AppCompatActivity(), CadastroView {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var presenter : CadastroPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CadastroPresenter(this,binding.root.context)
       // supportActionBar?.hide()

        binding.buttonVoltar.setOnClickListener { finish() }
        binding.buttonCadastrar.setOnClickListener { cadastrar() }
    }

    private fun cadastrar(){
        val nome = binding.editUsuarioCadastro.text.toString()
        val senha1 = binding.editSenhaCadastro.text.toString()
        val senha2 = binding.editSenhaConfirmarCadastro.text.toString()
        presenter.cadastrar(nome, senha1, senha2)
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