package com.apkdoandroid.helpdesk10.model.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoDeDados(context: Context) : SQLiteOpenHelper(context, nome,null, version) {

    companion object{
        private const val nome = "helpDesk.db"
        private const val version = 1

        private const val sql_usuario = "CREATE TABLE tb_usuario (id integer primary key autoincrement, nome varcahr(100),senha varchar(100))"
        private const val sql_helpDesk = "CREATE TABLE tb_helpDesck (id integer primary key autoincrement, patrimonio varchar(100), descricao text, solucao text, data_create varchar(50),data_encerramento varchar(50))"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sql_usuario)
        db.execSQL(sql_helpDesk)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}