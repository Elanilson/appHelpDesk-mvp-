package com.apkdoandroid.helpdesk10.view.activity

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apkdoandroid.helpdesk10.R
import com.apkdoandroid.helpdesk10.databinding.ActivityHomeBinding
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.presenter.HomePresenter
import com.apkdoandroid.helpdesk10.view.adapter.SolicitacoesAdapter
import com.apkdoandroid.helpdesk10.view.intefaces.HomeView
import com.apkdoandroid.helpdesk10.view.intefaces.OnHelpDeskListener

class HomeActivity : AppCompatActivity(), HomeView {
    private lateinit var  binding : ActivityHomeBinding
    private val adapter = SolicitacoesAdapter()
    private lateinit var presenter : HomePresenter
    private var buttonAndamento = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarSair.toolbar.setTitle("")
        setSupportActionBar(binding.toolbarSair.toolbar)

        presenter = HomePresenter(this,binding.root.context)

        binding.buttonMinhasSolicitacoes.setOnClickListener {
            binding.buttonMinhasSolicitacoes.setTextColor(resources.getColor(R.color.white))
            binding.buttonTodas.setTextColor(resources.getColor(R.color.white))
            binding.buttonMinhasSolicitacoes.background =resources.getDrawable(R.drawable.background_button_meus_chamados)
            binding.buttonTodas.background =resources.getDrawable(R.drawable.background_button_chamados)
           // binding.buttonMinhasSolicitacoes.setTextAppearance(R.style.buttonCustom_chamado_em_andamento)
          //  binding.buttonMinhasSolicitacoes.setBackgroundResource(R.drawable.background_button_meus_chamados)
            adapter.limpar()
            if(buttonAndamento){
                presenter.carregar_Solicitacoes_Em_Andamento()
            }else{
                presenter.carregar_Solicitacoes_finalizadas()

            }
        }
        binding.buttonTodas.setOnClickListener {
           //   binding.buttonTodas.setTextColor(resources.getColor(R.color.laranja))
            binding.buttonTodas.background =resources.getDrawable(R.drawable.background_button_meus_chamados)
            binding.buttonMinhasSolicitacoes.background =resources.getDrawable(R.drawable.background_button_chamados)
            // binding.buttonMinhasSolicitacoes.setTextAppearance(R.style.buttonCustom_chamado_em_andamento)
            //  binding.buttonMinhasSolicitacoes.setBackgroundResource(R.drawable.background_button_meus_chamados)
            adapter.limpar()
            presenter.carregarTodasSolicitacoes()
        }
        binding.buttonAndamento.setOnClickListener {
              binding.buttonAndamento.setTextColor(resources.getColor(R.color.vermleho500))
            binding.buttonAndamento.background =resources.getDrawable(R.drawable.background_button_chamado_em_andamento)
            binding.buttonFinalizadas.setTextColor(resources.getColor(R.color.white))
            binding.buttonFinalizadas.background =resources.getDrawable(R.drawable.background_button_chamados)
            //  binding.buttonMinhasSolicitacoes.setBackgroundResource(R.drawable.background_button_meus_chamados)
            // binding.buttonMinhasSolicitacoes.setTextAppearance(R.style.buttonCustom_chamado_em_andamento)
            buttonAndamento = true
            adapter.limpar()
            presenter.carregar_Solicitacoes_Em_Andamento()
        }
        binding.buttonFinalizadas.setOnClickListener {
              binding.buttonFinalizadas.setTextColor(resources.getColor(R.color.verde100))
            binding.buttonAndamento.setTextColor(resources.getColor(R.color.white))
            binding.buttonAndamento.background =resources.getDrawable(R.drawable.background_button_chamados)
            binding.buttonFinalizadas.background =resources.getDrawable(R.drawable.background_button_chamado_finalizado)
            // binding.buttonMinhasSolicitacoes.setTextAppearance(R.style.buttonCustom_chamado_em_andamento)
            //  binding.buttonMinhasSolicitacoes.setBackgroundResource(R.drawable.background_button_meus_chamados)
            buttonAndamento = false
            adapter.limpar()
            presenter.carregar_Solicitacoes_finalizadas()
        }

        binding.buttonNovaSolicitacao.setOnClickListener { startActivity(Intent(this,
            SolicitacaoActivity::class.java)) }
        val listener = object : OnHelpDeskListener{
            override fun onClick(helpDesk: HelpDesk) {

                val bundle = Bundle()
                bundle.putLong("id",helpDesk.id)
                val intent = Intent(binding.root.context,EncerrarSolicitacaoMainActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        }
        adapter.attackListener(listener)

        configurarRecyclewview()
    }


    private fun configurarRecyclewview(){
        binding.recyclerviewSolicitacoes.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewSolicitacoes.adapter = adapter
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sair,menu)
       // return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sair -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun mensagem(mensagem: String) {
        Toast.makeText(this,mensagem, Toast.LENGTH_SHORT).show()
    }

    override fun exibirSolicitacoes(solicitacoes: MutableList<HelpDesk>) {
       adapter.attackSolicitacoes(solicitacoes)
    }

    override fun status(status: Boolean) {
        if(status){
            binding.layoutSemSolicitacao.visibility = View.GONE
        }else{
            binding.layoutSemSolicitacao.visibility = View.VISIBLE

        }
    }

    override fun onResume() {
        super.onResume()
        presenter.carregar_Solicitacoes_Em_Andamento()
    }
}