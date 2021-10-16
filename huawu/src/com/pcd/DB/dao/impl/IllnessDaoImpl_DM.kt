package com.example.com.pcd.DB.dao.impl

import com.example.com.pcd.DB.dao.IllnessDao
import com.example.com.pcd.DB.db.DMDatabase
import com.example.com.pcd.entity.Illness
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.PreparedStatement
import java.sql.SQLException

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-16:04
 */
class IllnessDaoImpl_DM:IllnessDao() {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java.name)

    override fun insertIllness(image: Illness): Pair<String, String>? {
        val execute: Boolean?
        try {
            val connection = DMDatabase.connection()
            val prepareStatement = connection?.prepareStatement("insert into xinchuang.\"illness\" values(?,?,?)")
            image.id?.let { prepareStatement?.setInt(1, it) }
            image.illnessText?.let { prepareStatement?.setString(2, it) }
            image.time?.let { prepareStatement?.setString(3, it) }

            execute = prepareStatement?.execute()
        }catch (e: SQLException){
            logger.info("插入数据错误"+e.message)
            return Pair<String,String>("添加失败",e.message.toString())
        }

        var pair:Pair<String,String>? = null
        if (execute == false){
            pair = Pair("添加成功","没什么好说你 你是对的")
        }

        return pair
    }

    override fun queryAllIllness(): List<Illness> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"illness\"")

        return queryIllness(prepareStatement!!)
    }

    override fun queryIllnessById(id: Int): List<Illness> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"illness\" where \"id\" = ? ")
        prepareStatement?.setInt(1,id)

        return queryIllness(prepareStatement!!)
    }

    private fun queryIllness(preparedStatement: PreparedStatement):List<Illness>{
        val resultSet = preparedStatement.executeQuery()

        val mutableListOf = mutableListOf<Illness>()
        while (resultSet?.next() == true) {
            val id = resultSet.getInt(1)
            val illnessText = resultSet.getString(2)
            val time = resultSet.getString(3)

            val illness = Illness(id,illnessText, time)
            mutableListOf.add(illness)

        }
        return mutableListOf
    }
}