package com.apkdoandroid.helpdesk10.model.repositorio

import android.content.ContentValues
import android.content.Context
import com.apkdoandroid.helpdesk10.model.entities.Usuario
import com.apkdoandroid.helpdesk10.model.local.BancoDeDados
import java.lang.Exception

class UsuarioRepositorio private constructor(context: Context){

    private val db = BancoDeDados(context)
    private val escrever = db.writableDatabase
    private val ler = db.readableDatabase

    companion object{
        private lateinit var INSTANCE : UsuarioRepositorio
        fun getInstance(context: Context) : UsuarioRepositorio{
            if(!::INSTANCE.isInitialized){
                INSTANCE = UsuarioRepositorio(context)
            }
            return INSTANCE
        }
    }

    fun insert(usuario: Usuario) : Boolean{
        escrever.isOpen
        return try {
            val values = ContentValues()
            values.put("nome",usuario.nome)
            values.put("senha",usuario.senha)
            escrever.insert("tb_usuario",null,values)
            true
        }catch (e: Exception){
            println(e.message)
            false
        }finally {
            //escrever.close()
        }
    }

    fun getUsuario(usuario: Usuario) : Usuario?{
         var novoUsuario : Usuario? = null

        return try {
            val projecao = arrayOf("id","nome","senha")

            val where = "nome = ? and senha = ?"
            val whereArgs = arrayOf(usuario.nome,usuario.senha)

            var cursor = ler.query("tb_usuario",projecao,where,whereArgs,null,null,null)

            if(cursor != null && cursor.count > 0){
                cursor.moveToFirst()
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val senha = cursor.getString(cursor.getColumnIndexOrThrow("senha"))

                novoUsuario = Usuario(id, nome, senha)
            }
            cursor.close()

            novoUsuario
        }catch (e : Exception){
            println(e.message)
            novoUsuario
        }finally {
          //  ler.close()
        }
    }
}