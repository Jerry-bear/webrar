package com.example.com.pcd.DB.db

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSetMetaData
import java.sql.SQLException


/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/15-21:22
 */
object DMDatabase: Database() {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java.name)

    override val driver = "dm.jdbc.driver.DmDriver"

    override val username = "SYSDBA"

    override val password = "SYSDBA"

    override val url = "jdbc:dm://39.107.49.92:5236/SYSDBA?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8"

    private var connection:Connection? = null

    init {
        Class.forName(driver)
    }

    override fun connection(): Connection? {
        if (connection == null){
            try {
                connection = DriverManager.getConnection(url, username, password)
            }catch (e:SQLException){
                logger.info("数据库连接失败:"+e.message)
            }
        }
       return connection
    }


    override
    fun close(connection: Connection?){
        connection?.close()
    }

    override fun queryTableOfLabelName(table:String): List<String?> {


        //连接数据库
        val connection = connection()

        //执行语句
        val pstmt = connection?.prepareStatement("select * from xinchuang.\"${table}\"")

        //查询结构
        val resultSet = pstmt?.executeQuery()
        val rsmd: ResultSetMetaData? = resultSet?.metaData


        val mutableListOf = mutableListOf<String>()

        for (i in 1 ..rsmd?.columnCount!!){
            val labelName = rsmd.getColumnName(i)
            mutableListOf.add(labelName)
        }


        logger.info(table+"表的所有字段:"+ mutableListOf.toString())

        return mutableListOf
    }


}