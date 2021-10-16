package com.example.com.pcd.DB.dao.impl

import com.example.com.pcd.DB.dao.ImagesDao
import com.example.com.pcd.DB.db.DMDatabase
import com.example.com.pcd.entity.Images
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.PreparedStatement
import java.sql.SQLException

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-15:46
 */
class ImagesDaoImpl_DM:ImagesDao() {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java.name)

    override fun insertImages(image: Images):Pair<String, String>? {
        val execute: Boolean?
        try {
            val connection = DMDatabase.connection()
            val prepareStatement = connection?.prepareStatement("insert into xinchuang.\"images\" values(?,?,?,?,?,?,?,?,?)")
            image.id?.let { prepareStatement?.setInt(1, it) }
            image.imageName?.let { prepareStatement?.setString(2, it) }
            image.image?.let { prepareStatement?.setString(3, it) }
            image.year.let { prepareStatement?.setInt(4, it) }
            image.month.let { prepareStatement?.setInt(5, it) }
            image.day.let { prepareStatement?.setInt(6, it) }
            image.hour.let { prepareStatement?.setInt(7, it) }
            image.minutes.let { prepareStatement?.setInt(8, it) }
            image.seconds.let { prepareStatement?.setInt(9, it) }

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

    override fun queryAllImages(): List<Images> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"images\"")


        return queryImages(prepareStatement!!)
    }

    override fun queryImageById(id: Int): List<Images> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"images\" where \"id\" = ? ")
        prepareStatement?.setInt(1,id)

        return queryImages(prepareStatement!!)
    }

    private fun queryImages(preparedStatement: PreparedStatement):List<Images>{
        val resultSet = preparedStatement.executeQuery()

        val mutableListOf = mutableListOf<Images>()
        while (resultSet?.next() == true) {
            val id = resultSet.getInt(1)
            val imageName = resultSet.getString(2)
            val image = resultSet.getString(3)
            val year = resultSet.getInt(4)
            val month = resultSet.getInt(5)
            val day = resultSet.getInt(6)
            val hour = resultSet.getInt(7)
            val minutes = resultSet.getInt(8)
            val seconds = resultSet.getInt(9)

            val images = Images(id, imageName, image, year, month, day, hour, minutes, seconds)
            mutableListOf.add(images)

        }
        return mutableListOf
    }
}