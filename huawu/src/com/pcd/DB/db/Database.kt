package com.example.com.pcd.DB.db

import java.sql.Connection

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/15-21:58
 */
abstract class Database {
    open val url:String? = null
    open val username:String? = null
    open val password:String? = null
    open val driver:String? = null



    abstract fun connection():Connection?

    abstract fun queryTableOfLabelName(table:String): List<String?>
    abstract fun close(connection: Connection?)
}