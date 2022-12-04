package com.apkdoandroid.helpdesk10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apkdoandroid.helpdesk10.databinding.ActivityMainBinding
import com.apkdoandroid.helpdesk10.presenter.MainPrsenter
import com.apkdoandroid.helpdesk10.view.activity.CadastroActivity
import com.apkdoandroid.helpdesk10.view.activity.HomeActivity
import com.apkdoandroid.helpdesk10.view.intefaces.MainView

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  presenter : MainPrsenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  supportActionBar?.hide()
        presenter = MainPrsenter(this,binding.root.context)

        binding.buttonCadastrar.setOnClickListener { startActivity(Intent(this, CadastroActivity::class.java)) }
       // binding.buttonCadastrar.setOnClickListener { login() }
        binding.buttonEntrar.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }
     //   binding.buttonEntrar.setOnClickListener { login() }
    }

    private fun login(){
        val nome = binding.editUsuarioLogin.text.toString()
        val senha = binding.editSenhaLogin.text.toString()

        presenter.login(nome,senha)
    }

    override fun mensagem(mensagem: String) {
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show()
    }

    override fun status(status: Boolean) {
        if(status){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}