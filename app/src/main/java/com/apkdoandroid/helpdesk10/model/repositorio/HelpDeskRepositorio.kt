package com.apkdoandroid.helpdesk10.model.repositorio

import android.content.ContentValues
import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.HelpDesk
import com.apkdoandroid.helpdesk10.model.local.BancoDeDados

class HelpDeskRepositorio private constructor(context: Context){
    private  val db = BancoDeDados(context)
    private val escrever = db.writableDatabase
    private val ler = db.readableDatabase

    companion object {
        private lateinit var INSTANCE : HelpDeskRepositorio
        fun getInstance(context: Context) : HelpDeskRepositorio{
            if(!::INSTANCE.isInitialized){
                INSTANCE = HelpDeskRepositorio(context)

            }
            return  INSTANCE
        }
    }


    fun insert(helpDesk: HelpDesk) : Boolean{
        return try{
            val values = ContentValues()
            values.put("patrimonio",helpDesk.patrimonio)
            values.put("descricao",helpDesk.descricao)
            values.put("solucao",helpDesk.solucao)
            values.put("data_encerramento",helpDesk.data_encerramento)
            values.put("data_create",helpDesk.data_create)
            escrever.insert("tb_helpDesck",null,values)
            true
        }catch (e : Exception){
            println(e.message)
            false
        }finally {
           // escrever.close()
        }
    }
    fun update(helpDesk: HelpDesk) : Boolean{
        return try {
            val where = "id = ?"
            val whereArgs = arrayOf(helpDesk.id.toString())
            val values = ContentValues()
            values.put("solucao",helpDesk.solucao)
            values.put("data_encerramento",helpDesk.data_encerramento)
            escrever.update("tb_helpDesck",values,where,whereArgs)
            true
        }catch (e : Exception){
            println(e.message)
            false
        }finally {
            //escrever.close()
        }
    }

    fun get(id: Long) : HelpDesk? {
        var novoHelpDesk : HelpDesk? = null
        return try {
            val projection = arrayOf("id","patrimonio","descricao","solucao","data_create","data_encerramento")
            val where = "id = ?"
            val whereArgs = arrayOf(id.toString())

            val cursor = ler.query("tb_helpDesck",projection,where,whereArgs,null,null,null)
            if(cursor != null && cursor.count > 0){
                cursor.moveToFirst()
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val patrimonio = cursor.getString(cursor.getColumnIndexOrThrow("patrimonio"))
                val descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                val solucao = cursor.getString(cursor.getColumnIndexOrThrow("solucao"))
                val data_create = cursor.getString(cursor.getColumnIndexOrThrow("data_create"))
                val data_encerramento = cursor.getString(cursor.getColumnIndexOrThrow("data_encerramento"))

                novoHelpDesk = HelpDesk(id, patrimonio, descricao, solucao, data_create, data_encerramento)
            }
            novoHelpDesk
        }catch (e : java.lang.Exception){
            println(e.message)
            novoHelpDesk
        }finally {
          //  ler.close()
        }
    }

    fun getSolicitacoesAbertas() : MutableList<HelpDesk>{
        var lista : MutableList<HelpDesk> = arrayListOf()
        return  try {
            val projection = arrayOf("id","patrimonio","descricao","solucao","data_create","data_encerramento")
            val where = "data_encerramento = ?"
            val whereArgs = arrayOf("")

            val cursor = ler.rawQuery("select * from tb_helpDesck where data_encerramento = ''",null,null)
           // val cursor = ler.query("tb_helpDesck",projection,where,whereArgs,null,null,null)
            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                    val patrimonio = cursor.getString(cursor.getColumnIndexOrThrow("patrimonio"))
                    val descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                    val solucao = cursor.getString(cursor.getColumnIndexOrThrow("solucao"))
                    val data_create = cursor.getString(cursor.getColumnIndexOrThrow("data_create"))
                    val data_encerramento = cursor.getString(cursor.getColumnIndexOrThrow("data_encerramento"))

                    lista.add(HelpDesk(id, patrimonio, descricao, solucao, data_create, data_encerramento))
                }

            }

            lista
        }catch (e : java.lang.Exception){
            println(e.message)
            lista
        }finally {
           // ler.close()
        }
    }

    fun getSolicitacoesEncerradas() : MutableList<HelpDesk>{
        var lista : MutableList<HelpDesk> = arrayListOf()
        return  try {
            val projection = arrayOf("id","patrimonio","descricao","solucao","data_create","data_encerramento")
            val where = "data_encerramento != ?"
            val whereArgs = arrayOf("")

            val cursor = ler.rawQuery("select * from tb_helpDesck where data_encerramento != ''",null,null)
           // val cursor = ler.query("tb_helpDesck",projection,where,whereArgs,null,null,null)
            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                    val patrimonio = cursor.getString(cursor.getColumnIndexOrThrow("patrimonio"))
                    val descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                    val solucao = cursor.getString(cursor.getColumnIndexOrThrow("solucao"))
                    val data_create = cursor.getString(cursor.getColumnIndexOrThrow("data_create"))
                    val data_encerramento = cursor.getString(cursor.getColumnIndexOrThrow("data_encerramento"))

                    lista.add(HelpDesk(id, patrimonio, descricao, solucao, data_create, data_encerramento))
                }

            }

            lista
        }catch (e : java.lang.Exception){
            println(e.message)
            lista
        }finally {
          //  ler.close()
        }
    }

    fun getAll() : MutableList<HelpDesk>{
        var lista : MutableList<HelpDesk> = arrayListOf()
        return  try {
            val projection = arrayOf("id","patrimonio","descricao","solucao","data_create","data_encerramento")

            val cursor = ler.query("tb_helpDesck",projection,null,null,null,null,null)
            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                    val patrimonio = cursor.getString(cursor.getColumnIndexOrThrow("patrimonio"))
                    val descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                    val solucao = cursor.getString(cursor.getColumnIndexOrThrow("solucao"))
                    val data_create = cursor.getString(cursor.getColumnIndexOrThrow("data_create"))
                    val data_encerramento = cursor.getString(cursor.getColumnIndexOrThrow("data_encerramento"))

                    lista.add(HelpDesk(id, patrimonio, descricao, solucao, data_create, data_encerramento))
                }

            }

            lista
        }catch (e : java.lang.Exception){
            println("Error "+e.message)
            lista
        }finally {
           // ler.close()
        }
    }
}